package mockit.integration.springframework;

import org.junit.*;
import org.junit.rules.*;
import static org.junit.Assert.*;

import mockit.*;

import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.factory.support.*;

public final class SpringIntegrationTest
{
   @BeforeClass
   public static void applySpringIntegration() {
      new FakeBeanFactory();
   }

   @Rule public final ExpectedException thrown = ExpectedException.none();

   public static class ExampleSUT {
      @Autowired Collaborator collaborator;
      @Autowired Dependency dependency;
   }

   public interface Dependency {}
   static final class DependencyImpl implements Dependency {}
   public interface AnotherDependency {}
   static final class AnotherDependencyImpl implements AnotherDependency { Dependency subDep; }
   static class Collaborator { @Autowired Runnable action; }

   @Tested DependencyImpl dependency;
   @Tested(fullyInitialized = true) ExampleSUT exampleSUT;
   @Injectable Runnable action;

   @Tested
   Class<?> resolveAnotherDependency(Class<? extends AnotherDependency> anInterface) {
      assertSame(AnotherDependency.class, anInterface);
      return AnotherDependencyImpl.class;
   }

   @Test
   public void lookupTestedObjectsAndInjectedDependenciesThroughTheBeanFactory() {
      BeanFactory beanFactory = new DefaultListableBeanFactory();
      assertTestedObjectsAndDependencies(beanFactory);
   }

   @Test
   public void lookupTestedObjectsAndInjectedDependenciesThroughTheWebApplicationContext() {
      BeanFactory beanFactory = new TestWebApplicationContext();
      assertTestedObjectsAndDependencies(beanFactory);
   }

   @Test
   public void lookUpBeanByNameWithUnknownNameUsingBeanFactory() {
      BeanFactory beanFactory = new DefaultListableBeanFactory();
      assertNoSuchBeanDefinitionForUnknownBeanName(beanFactory);
   }

   @Test
   public void lookUpBeanByNameWithUnknownNameUsingWebApplicationContext() {
      BeanFactory beanFactory = new TestWebApplicationContext();
      assertNoSuchBeanDefinitionForUnknownBeanName(beanFactory);
   }

   @Test
   public void lookUpBeanByNameAndTypeWithUnknownNameAndTypeUsingBeanFactory() {
      BeanFactory beanFactory = new DefaultListableBeanFactory();
      assertNoSuchBeanDefinitionForUnknownBeanNameAndType(beanFactory);
   }

   @Test
   public void lookUpBeanByNameAndTypeWithUnknownNameAndTypeUsingWebApplicationContext() {
      BeanFactory beanFactory = new TestWebApplicationContext();
      assertNoSuchBeanDefinitionForUnknownBeanNameAndType(beanFactory);
   }

   @Test
   public void lookUpBeanByNameAndTypeWithWrongTypeUsingBeanFactory() {
      BeanFactory beanFactory = new DefaultListableBeanFactory();
      assertBeanNotOfRequiredTypeForWrongBeanType(beanFactory);
   }

   @Test
   public void lookUpBeanByNameAndTypeWithWrongTypeUsingWebApplicationContext() {
      BeanFactory beanFactory = new TestWebApplicationContext();
      assertBeanNotOfRequiredTypeForWrongBeanType(beanFactory);
   }

   @SuppressWarnings("ReuseOfLocalVariable")
   void assertTestedObjectsAndDependencies(BeanFactory beanFactory) {
      assertSame(dependency, exampleSUT.dependency);

      // Look-up beans by name only.
      Dependency dependencyBean = (Dependency) beanFactory.getBean("dependency");
      assertSame(dependency, dependencyBean);

      Collaborator collaboratorBean = (Collaborator) beanFactory.getBean("collaborator");
      assertSame(exampleSUT.collaborator, collaboratorBean);
      assertSame(action, exampleSUT.collaborator.action);

      ExampleSUT sut = (ExampleSUT) beanFactory.getBean("exampleSUT");
      assertSame(exampleSUT, sut);

      // Look-up beans by name and type.
      dependencyBean = beanFactory.getBean("dependency", Dependency.class);
      assertSame(dependency, dependencyBean);

      collaboratorBean = beanFactory.getBean("collaborator", Collaborator.class);
      assertSame(exampleSUT.collaborator, collaboratorBean);

      sut = beanFactory.getBean("exampleSUT", ExampleSUT.class);
      assertSame(exampleSUT, sut);

      AnotherDependency anotherDependencyBean = beanFactory.getBean("anotherDependency", AnotherDependency.class);
      assertNotNull(anotherDependencyBean);

      // Look-up beans by type only.
      dependencyBean = beanFactory.getBean(Dependency.class);
      assertSame(dependency, dependencyBean);

      collaboratorBean = beanFactory.getBean(Collaborator.class);
      assertSame(exampleSUT.collaborator, collaboratorBean);

      sut = beanFactory.getBean(ExampleSUT.class);
      assertSame(exampleSUT, sut);

      anotherDependencyBean = beanFactory.getBean(AnotherDependency.class);
      assertNotNull(anotherDependencyBean);
      assertSame(dependency, ((AnotherDependencyImpl) anotherDependencyBean).subDep);
   }

   void assertNoSuchBeanDefinitionForUnknownBeanName(BeanFactory beanFactory) {
      thrown.expect(NoSuchBeanDefinitionException.class);
      thrown.expectMessage("undefined");
      beanFactory.getBean("undefined");
   }

   void assertNoSuchBeanDefinitionForUnknownBeanNameAndType(BeanFactory beanFactory) {
      thrown.expect(NoSuchBeanDefinitionException.class);
      thrown.expectMessage("undefined");
      thrown.expectMessage("Process");
      beanFactory.getBean("undefined", Process.class);
   }

   void assertBeanNotOfRequiredTypeForWrongBeanType(BeanFactory beanFactory) {
      thrown.expect(BeanNotOfRequiredTypeException.class);
      thrown.expectMessage("Collaborator");
      beanFactory.getBean("dependency", Collaborator.class);
   }

   @Test
   public void lookUpBeanByTypeHavingInjectableInstance(@Injectable Collaborator collaborator) {
      BeanFactory beanFactory = new DefaultListableBeanFactory();

      Collaborator collaboratorBean = beanFactory.getBean(Collaborator.class);

      assertSame(collaborator, collaboratorBean);
   }

   @Test
   public void lookUpBeanByTypeTwiceHavingSingleInjectableInstance(@Injectable Collaborator collaborator) {
      BeanFactory beanFactory = new /*TestWebApplicationContext();*/DefaultListableBeanFactory();

      Collaborator collaboratorBean1 = beanFactory.getBean(Collaborator.class);
      Collaborator collaboratorBean2 = beanFactory.getBean(Collaborator.class);

      assertSame(collaborator, collaboratorBean1);
      assertSame(collaborator, collaboratorBean2);
   }

   static class Level1 { @Autowired Level2 level2; }
   static class Level2 { @Autowired Level3 level3; }
   static class Level3 { Level3(@SuppressWarnings("unused") String str) {} }

   @Test
   public void lookupBeanWithDependencyOnAnotherWhichAlsoDependsOnAnotherWhichHasAOneArgumentConstructor() {
      thrown.expect(IllegalStateException.class);
      thrown.expectMessage("for parameter \"str\" in constructor Level3(String str)");
      thrown.expectMessage("when initializing field \"Level3 level3\"");
      thrown.expectMessage("when initializing field \"Level2 level2\"");
      thrown.expectMessage("of @Tested object \"Level1 level1\"");

      BeanFactory beanFactory = new TestWebApplicationContext();
      Level1 level1 = beanFactory.getBean(Level1.class);

      assertNotNull(level1);
      assertNotNull(level1.level2);
      assertNotNull(level1.level2.level3);
   }

   @Test
   public void failToLookupBeanButCatchExceptionAndThrowAnother_stillShouldIncludedFilteredStackTrace() {
      BeanFactory beanFactory = new TestWebApplicationContext();

      try {
         beanFactory.getBean(Level1.class);
      }
      catch (IllegalStateException e) {
         for (StackTraceElement ste : e.getStackTrace()) {
            assertFalse(ste.toString(), ste.getClassName().startsWith("mockit.internal."));
         }
      }
   }
}
