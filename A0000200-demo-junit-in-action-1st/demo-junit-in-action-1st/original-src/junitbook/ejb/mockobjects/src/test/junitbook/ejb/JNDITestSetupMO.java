package junitbook.ejb;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;
import javax.naming.spi.NamingManager;

import com.mockobjects.naming.MockContext;

import junit.extensions.TestSetup;
import junit.framework.Test;

public class JNDITestSetupMO extends TestSetup
{
    private MockContext context;
    
    public JNDITestSetupMO(Test test)
    {
        super(test);
        context = new MockContext();
    }

    public MockContext getContext()
    {
        return this.context;        
    }
   
    protected void setUp() throws Exception
    {
        NamingManager.setInitialContextFactoryBuilder(
            new InitialContextFactoryBuilder()
            {
                public InitialContextFactory 
                    createInitialContextFactory(
                    Hashtable environment) throws NamingException
                {
                    return new InitialContextFactory() {
                        public Context getInitialContext(
                            Hashtable env) throws NamingException
                        {
                            return context; 
                        }                        
                    };
                }
            }
        );
    }
}
