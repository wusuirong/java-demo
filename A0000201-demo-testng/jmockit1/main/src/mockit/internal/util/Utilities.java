/*
 * Copyright (c) 2006 JMockit developers
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.internal.util;

import java.io.*;
import java.lang.reflect.*;
import java.math.*;
import java.net.*;
import java.security.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import javax.annotation.*;

import static java.lang.reflect.Modifier.isPublic;

import static mockit.internal.util.AutoBoxing.*;

/**
 * Miscellaneous utility constants and methods.
 */
public final class Utilities
{
   @Nonnull public static final Object[] NO_ARGS = {};
   public static final boolean JAVA8;
   public static final boolean HOTSPOT_VM;

   static {
      float javaVersion = Float.parseFloat(System.getProperty("java.specification.version"));
      JAVA8 = javaVersion >= 1.8F;
      String vmName = System.getProperty("java.vm.name");
      HOTSPOT_VM = vmName.contains("HotSpot") || vmName.contains("OpenJDK");
   }

   private Utilities() {}

   public static void ensureThatMemberIsAccessible(@Nonnull AccessibleObject classMember) {
      if (!classMember.isAccessible()) {
         classMember.setAccessible(true);
      }
   }

   @Nonnull
   public static Class<?> getClassType(@Nonnull Type declaredType) {
      while (true) {
         if (declaredType instanceof Class<?>) {
            return (Class<?>) declaredType;
         }

         if (declaredType instanceof ParameterizedType) {
            return (Class<?>) ((ParameterizedType) declaredType).getRawType();
         }

         if (declaredType instanceof GenericArrayType) {
            declaredType = ((GenericArrayType) declaredType).getGenericComponentType();
            continue;
         }

         if (declaredType instanceof TypeVariable) {
            declaredType = ((TypeVariable<?>) declaredType).getBounds()[0];
            continue;
         }

         if (declaredType instanceof WildcardType) {
            declaredType = ((WildcardType) declaredType).getUpperBounds()[0];
            continue;
         }

         throw new IllegalArgumentException("Type of unexpected kind: " + declaredType);
      }
   }

   public static boolean containsReference(@Nonnull List<?> references, @Nullable Object toBeFound) {
      for (int i = 0, n = references.size(); i < n; i++) {
         if (references.get(i) == toBeFound) {
            return true;
         }
      }

      return false;
   }

   public static int indexOfReference(@Nonnull Object[] references, @Nullable Object toBeFound) {
      for (int i = 0, n = references.length; i < n; i++) {
         if (references[i] == toBeFound) {
            return i;
         }
      }

      return -1;
   }

   public static boolean isClassAssignableTo(@Nonnull List<Class<?>> fromClasses, @Nonnull Class<?> toClass) {
      int n = fromClasses.size();

      for (int i = 0; i < n; i++) {
         Class<?> fromClass = fromClasses.get(i);

         if (toClass == fromClass || toClass.isAssignableFrom(fromClass)) {
            return true;
         }
      }

      return false;
   }

   @Nullable
   public static Class<?> findClassAssignableFrom(@Nonnull List<Class<?>> toClasses, @Nonnull Class<?> fromClass) {
      for (int i = 0; i < toClasses.size(); i++) {
         Class<?> toClass = toClasses.get(i);

         if (toClass == fromClass || toClass.isAssignableFrom(fromClass)) {
            return toClass;
         }
      }

      return null;
   }

   @Nullable
   public static Object convertFromString(@Nonnull Class<?> targetType, @Nonnull String value) {
      if (targetType == String.class) {
         return value;
      }
      else if (isCharacter(targetType)) {
         return value.charAt(0);
      }
      else if (targetType.isPrimitive()) {
         return newWrapperInstanceForPrimitiveType(targetType, value);
      }
      else if (isWrapperOfPrimitiveType(targetType)) {
         return newWrapperInstance(targetType, value);
      }
      else if (targetType == BigDecimal.class) {
         return new BigDecimal(value.trim());
      }
      else if (targetType == BigInteger.class) {
         return new BigInteger(value.trim());
      }
      else if (targetType == AtomicInteger.class) {
         return new AtomicInteger(Integer.parseInt(value.trim()));
      }
      else if (targetType == AtomicLong.class) {
         return new AtomicLong(Long.parseLong(value.trim()));
      }
      else if (targetType.isEnum()) {
         //noinspection unchecked
         return enumValue(targetType, value);
      }

      return null;
   }

   private static boolean isCharacter(@Nonnull Class<?> targetType) {
      return targetType == char.class || targetType == Character.class;
   }

   @Nonnull
   private static Object newWrapperInstanceForPrimitiveType(@Nonnull Class<?> targetType, @Nonnull String value) {
      Class<?> wrapperClass = getWrapperType(targetType);
      assert wrapperClass != null;
      return newWrapperInstance(wrapperClass, value);
   }

   @Nonnull
   private static Object newWrapperInstance(@Nonnull Class<?> wrapperClass, @Nonnull String value) {
      for (Constructor<?> constructor : wrapperClass.getDeclaredConstructors()) {
         if (isPublic(constructor.getModifiers())) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();

            if (parameterTypes.length == 1 && parameterTypes[0] == String.class) {
               //noinspection OverlyBroadCatchBlock
               try { return constructor.newInstance(value.trim()); } catch (Exception ignore) {}
            }
         }
      }

      throw new RuntimeException("Unable to instantiate " + wrapperClass + " with value \"" + value + '"');
   }

   @Nonnull
   private static <E extends Enum<E>> Object enumValue(Class<?> targetType, @Nonnull String value) {
      @SuppressWarnings("unchecked") Class<E> enumType = (Class<E>) targetType;
      return Enum.valueOf(enumType, value);
   }

   @Nonnull
   public static String getClassFileLocationPath(@Nonnull Class<?> aClass) {
      CodeSource codeSource = aClass.getProtectionDomain().getCodeSource();
      return getClassFileLocationPath(codeSource);
   }

   @Nonnull
   public static String getClassFileLocationPath(@Nonnull CodeSource codeSource) {
      String locationPath = codeSource.getLocation().getPath();
      try { locationPath = URLDecoder.decode(locationPath, "UTF-8"); } catch (UnsupportedEncodingException ignore) {}
      return locationPath;
   }
}
