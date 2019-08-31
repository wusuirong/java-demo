package junitbook.ejb.domain;

import java.util.Date;

import javax.ejb.CreateException;

import com.mockobjects.dynamic.C;

import junit.framework.Test;
import junitbook.ejb.CommonPetstoreTestCase;

public class TestOrderUtil extends CommonPetstoreTestCase
{       
    public static Test suite()
    {
        return suite(TestOrderUtil.class);
    } 

    protected void setUp() throws Exception
    {
        super.setUp();
        OrderUtil.clearCache();                
    }
    
    public void testGetOrderHomeOk() throws Exception
    {
        OrderLocalHome olh = OrderUtil.getOrderHome();
        assertNotNull(olh);
    }

    public void testGetOrderHomeFromCache() throws Exception
    {
        // First call to ensure the home is in the cache
        OrderUtil.getOrderHome();

        // Make sure the lookup method is NOT called thus proving 
        // the object is served from the cache
        jndiTestSetup.getMockContext().expectNotCalled("lookup");
        OrderUtil.getOrderHome();
    }

    public void testCreateOrderThrowsCreateException() 
        throws Exception
    {
        mockOrderLocalHome.expectAndThrow("create", C.ANY_ARGS,
            new CreateException("error"));
                    
        try
        {
            OrderUtil.createOrder(new Date(), "item 1");
            fail("Should have thrown a RuntimeException here");
        }
        catch (RuntimeException expected)
        {
            assertEquals("Failed to create "
                + "[junitbook.ejb.domain.OrderLocal]. Reason "
                + "[error]", expected.getMessage());
        }
    }
}
