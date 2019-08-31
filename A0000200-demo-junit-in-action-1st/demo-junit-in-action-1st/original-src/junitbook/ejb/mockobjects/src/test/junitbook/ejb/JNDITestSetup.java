package junitbook.ejb;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;
import javax.naming.spi.NamingManager;

import com.mockobjects.dynamic.Mock;

import junit.extensions.TestSetup;
import junit.framework.Test;

public class JNDITestSetup extends TestSetup
{
    private Mock mockContext;
    private Context context;
    
    public JNDITestSetup(Test test)
    {
        super(test);
        mockContext = new Mock(Context.class);
        context = (Context) mockContext.proxy();
    }

    public Mock getMockContext()
    {
        return this.mockContext;        
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
                            // Return the mock context here
                            return context; 
                        }                        
                    };
                }
            }
        );
    }
}
