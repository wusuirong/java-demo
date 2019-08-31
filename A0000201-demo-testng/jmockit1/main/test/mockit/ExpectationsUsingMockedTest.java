package mockit;

import java.beans.*;

import static org.junit.Assert.*;
import org.junit.*;

public final class ExpectationsUsingMockedTest
{
   public interface Dependency { String doSomething(boolean b); }

   public abstract static class AbstractBase {
      protected abstract boolean add(Integer i);
      final int doSomething() { return -1; }
   }

   @Mocked AbstractBase base;

   @Test
   public void multipleMockParametersOfSameMockedType(
      @Mocked final Dependency dependency1, @Mocked final Dependency dependency2
   ) {
      new Expectations() {{
         dependency1.doSomething(true); result = "1";
         dependency2.doSomething(false); result = "2";
      }};

      assertEquals("1", dependency1.doSomething(true));
      assertNull(dependency1.doSomething(false));
      assertEquals("2", dependency2.doSomething(false));
      assertNull(dependency2.doSomething(true));
   }

   @Test
   public void mockFieldForAbstractClass() {
      new Expectations() {{
         base.add(1); result = true;
      }};

      assertFalse(base.add(0));
      assertTrue(base.add(1));
      assertFalse(base.add(2));
   }

   static class ClassWithStaticInitializer2 {
      static boolean initialized = true;
      static int initialized() { return initialized ? 1 : -1; }
   }

   @Test
   public void doNotStubOutStaticInitializersByDefault(@Mocked ClassWithStaticInitializer2 unused) {
      assertEquals(0, ClassWithStaticInitializer2.initialized());
      assertTrue(ClassWithStaticInitializer2.initialized);
   }

   static class AnotherClassWithStaticInitializer {
      static boolean initialized = true;
      static int initialized() { return initialized ? 1 : -1; }
   }

   @Test
   public void mockEverythingWithoutStubbingStaticInitializers(@Mocked AnotherClassWithStaticInitializer unused) {
      assertEquals(0, AnotherClassWithStaticInitializer.initialized());
      assertTrue(AnotherClassWithStaticInitializer.initialized);
   }

   class InnerClass { int getValue() { return -1; } }

   @Test
   public void mockInnerClass(@Mocked final InnerClass innerMock) {
      assertEquals(0, innerMock.getValue());

      new Expectations() {{
         innerMock.getValue(); result = 123; times = 1;
      }};

      assertEquals(123, new InnerClass().getValue());
   }

   static final class SubClass extends AbstractBase { @Override protected boolean add(Integer i) { return false; } }

   @Test
   public void recordMethodFromAbstractBaseClassAndReplayOnSubclass() {
      new Expectations() {{ base.doSomething(); result = 1; }};

      int i = new SubClass().doSomething();

      assertEquals(1, i);
   }

   public interface BusinessInterface {}

   @Test
   public void getBeanInfoFromMockedInterface(@Mocked BusinessInterface mock) throws Exception {
      Class<? extends BusinessInterface> mockClass = mock.getClass();

      BeanInfo info = Introspector.getBeanInfo(mockClass);

      assertNotNull(info);
   }

   static class GenericBase<B extends Runnable> { public B base() { return null; } }
   public static final class GenericSubclass<S extends Runnable> extends GenericBase<S> { /* bridge method here */ }

   @Test
   public void recordExpectationOnBaseMethodHavingASyntheticBridgeMethodInSubclass(
      @Mocked final GenericSubclass<?> mock
   ) {
      new Expectations() {{
         mock.base();
         result = null;
      }};

      assertNull(mock.base());
   }
}
