package junitbook.ejb.service;

import java.util.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import junit.framework.TestCase;
import junitbook.ejb.util.JNDINames;

public class TestPetstoreEJB extends TestCase
{
    public void testCreateOrderOk() throws Exception
    {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY,
            "org.jnp.interfaces.NamingContextFactory");
        props.put(Context.PROVIDER_URL, "localhost:1099");
        props.put(Context.URL_PKG_PREFIXES, 
            "org.jboss.naming:org.jnp.interfaces");
        InitialContext context = new InitialContext(props);
        
        Object obj = context.lookup(JNDINames.PETSTORE_HOME);
        PetstoreHome petstoreHome = 
            (PetstoreHome) PortableRemoteObject.narrow(
                obj, PetstoreHome.class);

        Petstore petstore = petstoreHome.create();

        Date date = new Date();
        String item = "item 1";

        int orderId = petstore.createOrder(date, item);

        assertEquals(date.hashCode() + item.hashCode(), orderId);
    }
}