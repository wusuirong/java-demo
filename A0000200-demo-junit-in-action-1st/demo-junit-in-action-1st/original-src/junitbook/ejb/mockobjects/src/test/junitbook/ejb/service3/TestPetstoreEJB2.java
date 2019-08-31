package junitbook.ejb.service3;

import java.util.Date;

import javax.ejb.EJBException;
import javax.jms.JMSException;

import junit.framework.Test;
import junitbook.ejb.CommonPetstoreTestCase;
import junitbook.ejb.service.PetstoreEJB;

public class TestPetstoreEJB2 extends CommonPetstoreTestCase
{       
    private PetstoreEJB petstore;

    public static Test suite()
    {
        return suite(TestPetstoreEJB2.class);
    } 

    protected void setUp() throws Exception
    {
        super.setUp();
        
        petstore = new PetstoreEJB() {};
    }

    public void testCreateOrderOk() throws Exception
    {
        mockQueueConnectionFactory.expectAndReturn(
            "createQueueConnection", queueConnection);

        int orderId = petstore.createOrder(new Date(), "item1");
        
        assertEquals(1234, orderId);
    }

    public void testCreateThrowsOrderException() throws Exception
    {
        mockQueueConnectionFactory.expectAndThrow(
            "createQueueConnection", new JMSException("error"));

        try
        {
            petstore.createOrder(new Date(), "item1");
            fail("Should have thrown an EJBException");
        }
        catch (EJBException expected)
        {
            assertEquals("error", 
                expected.getCausedByException().getMessage());
        }
    }

}
