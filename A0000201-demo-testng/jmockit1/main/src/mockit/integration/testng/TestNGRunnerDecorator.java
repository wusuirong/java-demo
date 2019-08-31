/*
 * Copyright (c) 2006 JMockit developers
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.integration.testng;

import java.lang.reflect.*;
import javax.annotation.*;

import org.testng.*;
import org.testng.annotations.*;

import mockit.*;
import mockit.coverage.testRedundancy.*;
import mockit.integration.*;
import mockit.internal.state.*;
import static mockit.internal.util.StackTrace.*;

/**
 * Provides callbacks to be called by the TestNG 6.2+ test runner for each test execution.
 * JMockit will then assert any expectations recorded in {@link Expectations} subclasses during the test.
 * <p/>
 * This class is not supposed to be accessed from user code; it will be automatically loaded at startup.
 */
public final class TestNGRunnerDecorator extends TestRunnerDecorator implements IInvokedMethodListener, IExecutionListener
{
   @Nonnull private final ThreadLocal<SavePoint> savePoint = new ThreadLocal<>();

   @Override
   public void beforeInvocation(@Nonnull IInvokedMethod invokedMethod, @Nonnull ITestResult testResult) {
      ITestNGMethod testNGMethod = testResult.getMethod();
      Class<?> testClass = testResult.getTestClass().getRealClass();

      TestRun.clearNoMockingZone();

      if (!invokedMethod.isTestMethod()) {
         beforeConfigurationMethod(testNGMethod, testClass);
         return;
      }

      Method method = testNGMethod.getConstructorOrMethod().getMethod();
      exportCurrentTestMethodIfApplicable(method);

      Object testInstance = testResult.getInstance();

      if (testInstance == null || testInstance.getClass() != testClass) {
         // Happens when TestNG is running a JUnit test class, for which "TestResult#getInstance()" erroneously returns a
         // org.junit.runner.Description object.
         return;
      }

      TestRun.enterNoMockingZone();

      try {
         updateTestClassState(testInstance, testClass);
         TestRun.setRunningIndividualTest(testInstance);

         SavePoint testMethodSavePoint = new SavePoint();
         savePoint.set(testMethodSavePoint);

         if (shouldPrepareForNextTest) {
            TestRun.prepareForNextTest();
            shouldPrepareForNextTest = false;
            clearTestedObjectsCreatedDuringSetup();
         }

         createInstancesForTestedFieldsFromBaseClasses(testInstance);
         createInstancesForTestedFields(testInstance);
      }
      finally {
         TestRun.exitNoMockingZone();
      }
   }

   private static void exportCurrentTestMethodIfApplicable(@Nullable Method testMethod) {
      TestCoverage testCoverage = TestCoverage.INSTANCE;

      if (testCoverage != null) {
         testCoverage.setCurrentTestMethod(testMethod);
      }
   }

   private void beforeConfigurationMethod(@Nonnull ITestNGMethod method, @Nonnull Class<?> testClass) {
      TestRun.enterNoMockingZone();

      try {
         updateTestClassState(null, testClass);

         if (method.isBeforeMethodConfiguration()) {
            if (shouldPrepareForNextTest) {
               discardTestLevelMockedTypes();
               clearTestedObjectsCreatedDuringSetup();
            }

            Object testInstance = method.getInstance();
            updateTestClassState(testInstance, testClass);

            if (shouldPrepareForNextTest) {
               prepareForNextTest();
               shouldPrepareForNextTest = false;
               createInstancesForTestedFieldsBeforeSetup(testInstance);
            }

            TestRun.setRunningIndividualTest(testInstance);
         }
         else if (method.isAfterClassConfiguration()) {
            TestRun.getExecutingTest().setRecordAndReplay(null);
            cleanUpMocksFromPreviousTest();
            TestRun.clearCurrentTestInstance();
         }
         else if (!method.isAfterMethodConfiguration() && !method.isBeforeClassConfiguration()) {
            TestRun.getExecutingTest().setRecordAndReplay(null);
            cleanUpMocksFromPreviousTestClass();
            TestRun.clearCurrentTestInstance();
            TestRun.setCurrentTestClass(null);
         }
      }
      finally {
         TestRun.exitNoMockingZone();
      }
   }

   @Override
   public void afterInvocation(@Nonnull IInvokedMethod invokedMethod, @Nonnull ITestResult testResult) {
      if (!invokedMethod.isTestMethod()) {
         afterConfigurationMethod(testResult);
         return;
      }

      exportCurrentTestMethodIfApplicable(null);

      SavePoint testMethodSavePoint = savePoint.get();

      if (testMethodSavePoint == null) {
         return;
      }

      TestRun.enterNoMockingZone();
      shouldPrepareForNextTest = true;
      savePoint.set(null);

      Throwable thrownByTest = testResult.getThrowable();

      try {
         if (thrownByTest == null) {
            concludeTestExecutionWithNothingThrown(testMethodSavePoint, testResult);
         }
         else if (thrownByTest instanceof TestException) {
            concludeTestExecutionWithExpectedExceptionNotThrown(invokedMethod, testMethodSavePoint, testResult);
         }
         else if (testResult.isSuccess()) {
            concludeTestExecutionWithExpectedExceptionThrown(testMethodSavePoint, testResult, thrownByTest);
         }
         else {
            concludeTestExecutionWithUnexpectedExceptionThrown(testMethodSavePoint, testResult, thrownByTest);
         }
      }
      finally {
         TestRun.finishCurrentTestExecution();
         TestRun.clearCurrentTestInstance();
      }
   }

   private static void afterConfigurationMethod(@Nonnull ITestResult testResult) {
      TestRun.enterNoMockingZone();

      try {
         ITestNGMethod method = testResult.getMethod();

         if (method.isAfterMethodConfiguration()) {
            Throwable thrownAfterTest = testResult.getThrowable();

            if (thrownAfterTest != null) {
               filterStackTrace(thrownAfterTest);
            }
         }
      }
      finally {
         TestRun.exitNoMockingZone();
      }
   }

   private static void concludeTestExecutionWithNothingThrown(@Nonnull SavePoint testMethodSavePoint, @Nonnull ITestResult testResult) {
      try {
         concludeTestMethodExecution(testMethodSavePoint, null, false);
      }
      catch (Throwable t) {
         filterStackTrace(t);
         testResult.setThrowable(t);
         testResult.setStatus(ITestResult.FAILURE);
      }
   }

   private static void concludeTestExecutionWithExpectedExceptionNotThrown(
      @Nonnull IInvokedMethod invokedMethod, @Nonnull SavePoint testMethodSavePoint, @Nonnull ITestResult testResult
   ) {
      try {
         concludeTestMethodExecution(testMethodSavePoint, null, false);
      }
      catch (Throwable t) {
         filterStackTrace(t);

         if (isExpectedException(invokedMethod, t)) {
            testResult.setThrowable(null);
            testResult.setStatus(ITestResult.SUCCESS);
         }
         else {
            filterStackTrace(testResult.getThrowable());
         }
      }
   }

   private static void concludeTestExecutionWithExpectedExceptionThrown(
      @Nonnull SavePoint testMethodSavePoint, @Nonnull ITestResult testResult, @Nonnull Throwable thrownByTest
   ) {
      filterStackTrace(thrownByTest);

      try {
         concludeTestMethodExecution(testMethodSavePoint, thrownByTest, true);
      }
      catch (Throwable t) {
         if (t != thrownByTest) {
            filterStackTrace(t);
            testResult.setThrowable(t);
            testResult.setStatus(ITestResult.FAILURE);
         }
      }
   }

   private static void concludeTestExecutionWithUnexpectedExceptionThrown(
      @Nonnull SavePoint testMethodSavePoint, @Nonnull ITestResult testResult, @Nonnull Throwable thrownByTest
   ) {
      filterStackTrace(thrownByTest);

      try {
         concludeTestMethodExecution(testMethodSavePoint, thrownByTest, false);
      }
      catch (Throwable ignored) {}
   }

   private static boolean isExpectedException(@Nonnull IInvokedMethod invokedMethod, @Nonnull Throwable thrownByTest) {
      Method testMethod = invokedMethod.getTestMethod().getConstructorOrMethod().getMethod();
      Class<?>[] expectedExceptions = testMethod.getAnnotation(Test.class).expectedExceptions();
      Class<? extends Throwable> thrownExceptionType = thrownByTest.getClass();

      for (Class<?> expectedException : expectedExceptions) {
         if (expectedException.isAssignableFrom(thrownExceptionType)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void onExecutionStart() {}

   @Override
   public void onExecutionFinish() {
      TestRun.enterNoMockingZone();

      try {
         TestRunnerDecorator.cleanUpAllMocks();
      }
      finally {
         // Maven Surefire, somehow, runs these methods twice per test run.
         TestRun.clearNoMockingZone();
      }
   }
}
