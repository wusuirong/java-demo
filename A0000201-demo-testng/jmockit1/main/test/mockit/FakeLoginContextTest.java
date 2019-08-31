package mockit;

import java.util.*;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import javax.security.auth.spi.*;

import org.junit.*;
import org.junit.rules.*;
import static org.junit.Assert.*;

public final class FakeLoginContextTest
{
   @Rule public final ExpectedException thrown = ExpectedException.none();

   @Test
   public void fakeJREMethodAndConstructorUsingFakeClass() throws Exception {
      new FakeLoginContext();

      new LoginContext("test", (CallbackHandler) null).login();
   }

   public static class FakeLoginContext extends MockUp<LoginContext> {
      @Mock
      public void $init(String name, CallbackHandler callbackHandler) {
         assertEquals("test", name);
         assertNull(callbackHandler);
      }

      @Mock
      public void login() {}

      @Mock
      public Subject getSubject() { return null; }
   }

   @Test
   public void fakeJREMethodAndConstructorWithFakeClass() throws Exception {
      thrown.expect(LoginException.class);

      new MockUp<LoginContext>() {
         @Mock
         void $init(String name) { assertEquals("test", name); }

         @Mock
         void login() throws LoginException { throw new LoginException(); }
      };

      new LoginContext("test").login();
   }

   @Test
   public void fakeJREClassWithStubs() throws Exception {
      new FakeLoginContextWithStubs();

      LoginContext context = new LoginContext("");
      context.login();
      context.logout();
   }

   final class FakeLoginContextWithStubs extends MockUp<LoginContext> {
      @Mock void $init(String s) {}
      @Mock void logout() {}
      @Mock void login() {}
   }

   @Test
   public void accessFakedInstance() throws Exception {
      new MockUp<LoginContext>() {
         Subject testSubject;

         @Mock
         void $init(Invocation inv, String name, Subject subject) {
            assertNotNull(name);
            assertNotNull(subject);
            LoginContext it = inv.getInvokedInstance();
            assertNotNull(it);
            assertEquals(1, inv.getInvocationCount());
         }

         @Mock
         void login(Invocation inv) {
            LoginContext it = inv.getInvokedInstance();
            assertNull(it.getSubject()); // returns null until the subject is authenticated
            testSubject = new Subject();
         }

         @Mock
         void logout() { testSubject = null; }

         @Mock
         Subject getSubject() { return testSubject; }
      };

      LoginContext fakedInstance = new LoginContext("test", new Subject());
      assertNull(fakedInstance.getSubject());
      fakedInstance.login();
      assertNotNull(fakedInstance.getSubject());
      fakedInstance.logout();
      assertNull(fakedInstance.getSubject());
   }

   @Test
   public void proceedIntoRealImplementationsOfFakedMethods() throws Exception {
      // Create objects to be exercised by the code under test:
      Configuration configuration = new Configuration() {
         @Override
         public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
            Map<String, ?> options = Collections.emptyMap();

            return new AppConfigurationEntry[] {
               new AppConfigurationEntry(
                  TestLoginModule.class.getName(), AppConfigurationEntry.LoginModuleControlFlag.SUFFICIENT, options)
            };
         }
      };

      LoginContext loginContext = new LoginContext("test", null, null, configuration);

      // Apply fakes:
      ProceedingFakeLoginContext fakeInstance = new ProceedingFakeLoginContext();

      // Exercise the code under test:
      assertNull(loginContext.getSubject());
      loginContext.login();
      assertNotNull(loginContext.getSubject());
      assertTrue(fakeInstance.loggedIn);

      fakeInstance.ignoreLogout = true;
      loginContext.logout();
      assertTrue(fakeInstance.loggedIn);

      fakeInstance.ignoreLogout = false;
      loginContext.logout();
      assertFalse(fakeInstance.loggedIn);
   }

   static final class ProceedingFakeLoginContext extends MockUp<LoginContext> {
      boolean ignoreLogout;
      boolean loggedIn;

      @Mock
      void login(Invocation inv) {
         LoginContext it = inv.getInvokedInstance();

         try {
            inv.proceed();
            loggedIn = true;
         }
         finally {
            it.getSubject();
         }
      }

      @Mock
      void logout(Invocation inv) {
         if (!ignoreLogout) {
            inv.proceed();
            loggedIn = false;
         }
      }
   }

   public static class TestLoginModule implements LoginModule {
      @Override
      public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {}

      @Override public boolean login() { return true; }
      @Override public boolean commit() { return true; }
      @Override public boolean abort() { return false; }
      @Override public boolean logout() { return true; }
   }
}
