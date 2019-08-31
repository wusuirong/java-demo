package junitbook.ejb.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDIUtil
{
    private static InitialContext jndiContext;

    private static InitialContext getInitialContext()
    {
        if (jndiContext == null)
        {
            try
            {
                jndiContext = new InitialContext();
            }
            catch (NamingException e)
            {
                throw new RuntimeException(
                    "Failed to create InitialContext [" 
                    + e.getMessage() + "]");
            }
        }
        return jndiContext;
    }

    public static Object lookup(String name)
    {
        Object object = null;
        try
        {
            object = getInitialContext().lookup(name);
        }
        catch (NamingException e)
        {
            throw new RuntimeException("JNDI lookup failure for [" 
                + name + "]. Reason [" + e.getMessage() + "]");
        }

        return object;
    }

}
