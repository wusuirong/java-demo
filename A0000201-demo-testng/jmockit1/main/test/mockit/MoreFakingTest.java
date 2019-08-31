package mockit;

import java.lang.reflect.*;
import javax.accessibility.*;
import javax.faces.application.*;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.*;

import mockit.MoreFakingTest.ClassWithNested.*;

@SuppressWarnings("JUnitTestMethodWithNoAssertions")
public final class MoreFakingTest
{
   @Rule public final ExpectedException thrown = ExpectedException.none();

   final CodeUnderTest codeUnderTest = new CodeUnderTest();
   boolean fakeExecuted;

   static class CodeUnderTest {
      private final Collaborator dependency = new Collaborator();

      void doSomething() { dependency.provideSomeService(); }
      long doSomethingElse(int i) { return dependency.getThreadSpecificValue(i); }
   }

   public static class Collaborator {
      static Object xyz;
      protected int value;

      public Collaborator() {}
      Collaborator(int value) { this.value = value; }

      @SuppressWarnings("DeprecatedIsStillUsed")
      @Deprecated protected static String doInternal() { return "123"; }

      public void provideSomeService() { throw new RuntimeException("Real provideSomeService() called"); }

      public int getValue() { return value; }
      void setValue(int value) { this.value = value; }

      protected long getThreadSpecificValue(int i) { return Thread.currentThread().getId() + i; }
   }

   static class FakeCollaborator1 extends MockUp<Collaborator> {
      @Mock void provideSomeService() {}
   }

   @Test
   public void fakeDoingNothing() {
      new FakeCollaborator1();

      codeUnderTest.doSomething();
   }

   @Test
   public void applyFakesFromInnerFakeClassWithFakeConstructor() {
      new FakeCollaborator4();
      assertFalse(fakeExecuted);

      new CodeUnderTest().doSomething();

      assertTrue(fakeExecuted);
   }

   class FakeCollaborator4 extends MockUp<Collaborator> {
      @Mock void $init() { fakeExecuted = true; }
      @Mock void provideSomeService() {}
   }

   @Test
   public void applyReentrantFake() {
      thrown.expect(RuntimeException.class);

      new FakeCollaboratorWithReentrantFakeMethod();

      codeUnderTest.doSomething();
   }

   static class FakeCollaboratorWithReentrantFakeMethod extends MockUp<Collaborator> {
      @Mock int getValue() { return 123; }
      @Mock void provideSomeService(Invocation inv) { inv.proceed(); }
   }

   @Test
   public void applyFakeForConstructor() {
      new FakeCollaboratorWithConstructorFake();

      new FacesMessage("test");
   }

   static class FakeCollaboratorWithConstructorFake extends MockUp<FacesMessage> {
      @Mock
      void $init(String value) {
         assertEquals("test", value);
      }
   }

   public static class SubCollaborator extends Collaborator {
      public SubCollaborator(int i) { throw new RuntimeException(String.valueOf(i)); }

      @Override
      public void provideSomeService() { value = 123; }
   }

   @Test
   public void applyFakeForClassHierarchy() {
      new MockUp<SubCollaborator>() {
         @Mock
         void $init(Invocation inv, int i) {
            assertNotNull(inv.getInvokedInstance());
            assertTrue(i > 0);
         }

         @Mock
         void provideSomeService(Invocation inv) {
            SubCollaborator it = inv.getInvokedInstance();
            it.value = 45;
         }

         @Mock
         int getValue(Invocation inv) {
            SubCollaborator it = inv.getInvokedInstance();
            assertNotNull(it);
            return 123;
         }
      };

      SubCollaborator collaborator = new SubCollaborator(123);
      collaborator.provideSomeService();
      assertEquals(45, collaborator.value);
      assertEquals(123, collaborator.getValue());
   }

   @Test
   public void applyFakeForJREClass() {
      FakeThread fakeThread = new FakeThread();

      Thread.currentThread().interrupt();

      assertTrue(fakeThread.interrupted);
   }

   public static class FakeThread extends MockUp<Thread> {
      boolean interrupted;

      @Mock
      public void interrupt() { interrupted = true; }
   }

   @Test
   public void fakeStaticInitializer() {
      new MockUp<AccessibleState>() {
         @Mock void $clinit() {}
      };

      assertNull(AccessibleState.ACTIVE);
   }

   abstract static class AnAbstractClass { protected abstract int doSomething(); }

   @Test
   public <A extends AnAbstractClass> void fakeAbstractClassWithFakeForAbstractMethodHavingInvocationParameter() {
      final AnAbstractClass obj = new AnAbstractClass() { @Override protected int doSomething() { return 0; } };

      new MockUp<A>() {
         @Mock
         int doSomething(Invocation inv) {
            assertSame(obj, inv.getInvokedInstance());
            Method invokedMethod = inv.getInvokedMember();
            assertTrue(AnAbstractClass.class.isAssignableFrom(invokedMethod.getDeclaringClass()));
            return 123;
         }
      };

      assertEquals(123, obj.doSomething());
   }

   static class GenericClass<T> { protected T doSomething() { return null; } }

   @Test
   public void fakeGenericClassWithFakeHavingInvocationParameter() {
      new MockUp<GenericClass<String>>() {
         @Mock String doSomething(Invocation inv) { return "faked"; }
      };

      GenericClass<String> faked = new GenericClass<>();
      assertEquals("faked", faked.doSomething());
   }

   @Test @SuppressWarnings("MethodWithMultipleLoops")
   public void concurrentFake() throws Exception {
      new MockUp<Collaborator>() {
         @Mock long getThreadSpecificValue(int i) { return Thread.currentThread().getId() + 123; }
      };

      Thread[] threads = new Thread[5];

      for (int i = 0; i < threads.length; i++) {
         threads[i] = new Thread() {
            @Override
            public void run() {
               long threadSpecificValue = Thread.currentThread().getId() + 123;
               long actualValue = new CodeUnderTest().doSomethingElse(0);
               assertEquals(threadSpecificValue, actualValue);
            }
         };
      }

      for (Thread thread : threads) { thread.start(); }
      for (Thread thread : threads) { thread.join(); }
   }

   @Test
   public void fakeAffectsInstancesOfSpecifiedSubclassAndNotOfBaseClass() {
      new FakeForSubclass();

      // Faking applies to instance methods executed on instances of the subclass:
      assertEquals(123, new SubCollaborator(5).getValue());

      // And to static methods from any class in the hierarchy:
      //noinspection deprecation
      assertEquals("faked", Collaborator.doInternal());

      // But not to instance methods executed on instances of the base class:
      assertEquals(62, new Collaborator(62).getValue());
   }

   static class FakeForSubclass extends MockUp<SubCollaborator> {
      @Mock void $init(int i) {}
      @Mock String doInternal() { return "faked"; }
      @Mock int getValue() { return 123; }
   }

   public static final class ClassWithNested {
      public static void doSomething() {}
      public static final class Nested {}
   }

   @Test
   public void fakeAClassHavingANestedClass() {
      new MockUp<ClassWithNested>() {
         @Mock
         void doSomething() {}
      };

      Class<?> outerClass = Nested.class.getDeclaringClass();

      assertSame(ClassWithNested.class, outerClass);
   }
}
