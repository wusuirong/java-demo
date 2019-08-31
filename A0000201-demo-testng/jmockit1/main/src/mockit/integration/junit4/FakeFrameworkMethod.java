/*
 * Copyright (c) 2006 JMockit developers
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.integration.junit4;

import java.lang.annotation.*;
import java.util.*;
import javax.annotation.*;

import org.junit.runners.model.*;

import mockit.*;
import mockit.internal.faking.*;
import mockit.internal.util.*;

/**
 * Startup fake that modifies the JUnit 4.5+ test runner so that it calls back to JMockit immediately after every test executes.
 * When that happens, JMockit will assert any expectations recorded during the test in {@link Expectations} subclasses.
 * <p/>
 * This class is not supposed to be accessed from user code. JMockit will automatically load it at startup.
 */
public final class FakeFrameworkMethod extends MockUp<FrameworkMethod>
{
   @Nonnull private final JUnit4TestRunnerDecorator decorator = new JUnit4TestRunnerDecorator();

   @Nullable @Mock
   public Object invokeExplosively(@Nonnull Invocation invocation, Object target, Object... params) throws Throwable {
      return decorator.invokeExplosively((FakeInvocation) invocation, target, params);
   }

   @Mock
   public static void validatePublicVoidNoArg(@Nonnull Invocation invocation, boolean isStatic, List<Throwable> errors) {
      FrameworkMethod it = invocation.getInvokedInstance();
      assert it != null;

      int previousErrorCount = errors.size();

      if (!isStatic && eachParameterContainsAKnownAnnotation(it.getMethod().getParameterAnnotations())) {
         it.validatePublicVoid(false, errors);
      }
      else {
         ((FakeInvocation) invocation).prepareToProceedFromNonRecursiveMock();
         it.validatePublicVoidNoArg(isStatic, errors);
      }

      int errorCount = errors.size();

      for (int i = previousErrorCount; i < errorCount; i++) {
         Throwable errorAdded = errors.get(i);
         StackTrace.filterStackTrace(errorAdded);
      }
   }

   private static boolean eachParameterContainsAKnownAnnotation(@Nonnull Annotation[][] parametersAndTheirAnnotations) {
      if (parametersAndTheirAnnotations.length == 0) {
         return false;
      }

      for (Annotation[] parameterAnnotations : parametersAndTheirAnnotations) {
         if (!containsAKnownAnnotation(parameterAnnotations)) {
            return false;
         }
      }

      return true;
   }

   private static boolean containsAKnownAnnotation(@Nonnull Annotation[] parameterAnnotations) {
      if (parameterAnnotations.length == 0) {
         return false;
      }

      for (Annotation parameterAnnotation : parameterAnnotations) {
         Class<? extends Annotation> annotationType = parameterAnnotation.annotationType();
         String annotationTypeName = annotationType.getName();

         if (
            "mockit.Tested mockit.Mocked mockit.Injectable mockit.Capturing".contains(annotationTypeName) ||
            annotationType.isAnnotationPresent(Tested.class)
         ) {
            return true;
         }
      }

      return false;
   }
}
