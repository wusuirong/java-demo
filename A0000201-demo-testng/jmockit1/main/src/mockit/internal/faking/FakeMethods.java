/*
 * Copyright (c) 2006 JMockit developers
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.internal.faking;

import java.lang.reflect.*;
import java.util.*;
import javax.annotation.*;
import static java.lang.reflect.Modifier.*;

import mockit.internal.*;
import mockit.internal.reflection.*;
import mockit.internal.state.*;
import mockit.internal.util.*;
import mockit.internal.reflection.GenericTypeReflection.*;
import static mockit.internal.util.ObjectMethods.*;

/**
 * A container for the fake methods "collected" from a fake class.
 */
final class FakeMethods
{
   @Nonnull private final Class<?> realClass;
   private final boolean targetTypeIsAClass;
   private final boolean reentrantRealClass;
   @Nonnull private final List<FakeMethod> methods;
   @Nullable private FakeMethod adviceMethod;
   @Nonnull private final GenericTypeReflection typeParametersToTypeArguments;
   @Nonnull private String fakeClassInternalName;
   @Nullable private List<FakeState> fakeStates;

   final class FakeMethod {
      private final int access;
      @Nonnull final String name;
      @Nonnull final String desc;
      private final boolean isByNameOnly;
      final boolean isAdvice;
      @Nonnull final String fakeDescWithoutInvocationParameter;
      private boolean hasMatchingRealMethod;
      @Nullable private GenericSignature fakeSignature;
      private int indexForFakeState;
      private boolean nativeRealMethod;

      private FakeMethod(int access, @Nonnull String name, @Nonnull String desc) {
         this.access = access;
         this.name = name;
         this.desc = desc;

         if (desc.contains("Lmockit/Invocation;")) {
            fakeDescWithoutInvocationParameter = '(' + desc.substring(20);
            isByNameOnly = name.charAt(0) != '$' && fakeDescWithoutInvocationParameter.startsWith("()");
            isAdvice = "$advice".equals(name) && "()Ljava/lang/Object;".equals(fakeDescWithoutInvocationParameter);
         }
         else {
            fakeDescWithoutInvocationParameter = desc;
            isByNameOnly = false;
            isAdvice = false;
         }

         hasMatchingRealMethod = false;
         indexForFakeState = -1;
      }

      @SuppressWarnings("StringEquality") boolean hasInvocationParameter() { return desc != fakeDescWithoutInvocationParameter; }
      boolean hasInvocationParameterOnly() { return isByNameOnly || isAdvice; }

      boolean isMatch(int realAccess, @Nonnull String realName, @Nonnull String realDesc, @Nullable String signature) {
         if (name.equals(realName) && hasMatchingParameters(realDesc, signature)) {
            hasMatchingRealMethod = true;
            nativeRealMethod = isNative(realAccess);
            return true;
         }

         return false;
      }

      private boolean hasMatchingParameters(@Nonnull String methodDesc, @Nullable String signature) {
         boolean sameParametersIgnoringGenerics = fakeDescWithoutInvocationParameter.equals(methodDesc);

         if (sameParametersIgnoringGenerics || signature == null) {
            return sameParametersIgnoringGenerics;
         }

         if (fakeSignature == null) {
            fakeSignature = typeParametersToTypeArguments.parseSignature(fakeDescWithoutInvocationParameter);
         }

         return fakeSignature.satisfiesGenericSignature(signature);
      }

      boolean isMatchByName(@Nonnull String realName) { return isByNameOnly && name.equals(realName); }

      @Nonnull Class<?> getRealClass() { return realClass; }
      @Nonnull String getFakeNameAndDesc() { return name + desc; }
      int getIndexForFakeState() { return indexForFakeState; }

      boolean isStatic() { return Modifier.isStatic(access); }
      boolean isPublic() { return Modifier.isPublic(access); }
      boolean isForGenericMethod() { return fakeSignature != null; }
      boolean isForNativeMethod() { return nativeRealMethod; }
      boolean requiresFakeState() { return hasInvocationParameter() || reentrantRealClass; }
      boolean canBeReentered() { return targetTypeIsAClass && !nativeRealMethod; }

      @Override @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
      public boolean equals(Object obj) {
         FakeMethod other = (FakeMethod) obj;
         return realClass == other.getRealClass() && name.equals(other.name) && desc.equals(other.desc);
      }

      @Override
      public int hashCode() {
         return 31 * (31 * realClass.hashCode() + name.hashCode()) + desc.hashCode();
      }
   }

   FakeMethods(@Nonnull Class<?> realClass, @Nonnull Type targetType) {
      this.realClass = realClass;

      if (realClass == targetType) {
         targetTypeIsAClass = true;
      }
      else {
         Class<?> targetClass = Utilities.getClassType(targetType);
         targetTypeIsAClass = !targetClass.isInterface();
      }

      reentrantRealClass = targetTypeIsAClass && ClassLoadingBridge.instanceOfClassThatParticipatesInClassLoading(realClass);
      methods = new ArrayList<>();
      typeParametersToTypeArguments = new GenericTypeReflection(realClass, targetType);
      fakeClassInternalName = "";
   }

   @Nonnull Class<?> getRealClass() { return realClass; }

   @Nullable
   FakeMethod addMethod(boolean fromSuperClass, int access, @Nonnull String name, @Nonnull String desc) {
      if (fromSuperClass && isMethodAlreadyAdded(name, desc)) {
         return null;
      }

      FakeMethod fakeMethod = new FakeMethod(access, name, desc);

      if (fakeMethod.isAdvice) {
         adviceMethod = fakeMethod;
      }
      else {
         methods.add(fakeMethod);
      }

      return fakeMethod;
   }

   private boolean isMethodAlreadyAdded(@Nonnull String name, @Nonnull String desc) {
      int p = desc.lastIndexOf(')');
      String params = desc.substring(0, p + 1);

      for (FakeMethod fakeMethod : methods) {
         if (fakeMethod.name.equals(name) && fakeMethod.desc.startsWith(params)) {
            return true;
         }
      }

      return false;
   }

   void addFakeState(@Nonnull FakeState fakeState) {
      if (fakeStates == null) {
         fakeStates = new ArrayList<>(4);
      }

      fakeState.fakeMethod.indexForFakeState = fakeStates.size();
      fakeStates.add(fakeState);
   }

   /**
    * Finds a fake method with the same signature of a given real method, if previously collected from the fake class.
    * Also handles "match-by-name-only" fake methods with only the <tt>Invocation</tt> parameter, and the <tt>$advice</tt> fake method.
    * <p/>
    * This operation can be performed only once for any given fake method in this container, so that after the last real method is processed
    * there should be no fake methods left unused in the container.
    */
   @Nullable
   FakeMethod findMethod(int access, @Nonnull String name, @Nonnull String desc, @Nullable String signature) {
      FakeMethod fakeMethodMatchingByNameOnly = null;

      for (FakeMethod fakeMethod : methods) {
         if (fakeMethod.isMatch(access, name, desc, signature)) {
            return fakeMethod;
         }

         if (fakeMethod.isMatchByName(name)) {
            fakeMethodMatchingByNameOnly = fakeMethod;
         }
      }

      if (fakeMethodMatchingByNameOnly != null) {
         return fakeMethodMatchingByNameOnly;
      }

      if (adviceMethod != null && !isNative(access) && !isConstructorOrClassInitialization(name) && !isMethodFromObject(name, desc)) {
         return adviceMethod;
      }

      return null;
   }

   private static boolean isConstructorOrClassInitialization(@Nonnull String memberName) {
      return "$init".equals(memberName) || "$clinit".equals(memberName);
   }

   @Nonnull String getFakeClassInternalName() { return fakeClassInternalName; }
   void setFakeClassInternalName(@Nonnull String fakeClassInternalName) { this.fakeClassInternalName = fakeClassInternalName.intern(); }

   boolean hasUnusedFakes() {
      if (adviceMethod != null) {
         return true;
      }

      for (FakeMethod method : methods) {
         if (!method.hasMatchingRealMethod) {
            return true;
         }
      }

      return false;
   }

   void registerFakeStates(@Nonnull Object fake, boolean forStartupFake) {
      if (fakeStates != null) {
         FakeStates allFakeStates = TestRun.getFakeStates();

         if (forStartupFake) {
            allFakeStates.addStartupFakeAndItsFakeStates(fake, fakeStates);
         }
         else {
            allFakeStates.addFakeAndItsFakeStates(fake, fakeStates);
         }
      }
   }
}
