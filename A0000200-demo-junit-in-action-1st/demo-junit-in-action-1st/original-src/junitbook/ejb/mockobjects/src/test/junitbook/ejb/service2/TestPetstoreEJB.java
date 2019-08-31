package junitbook.ejb.service2;

import java.util.Date;

import javax.ejb.EJBException;
import javax.jms.JMSException;

import com.mockobjects.dynamic.Mock;

import junit.framework.TestCase;
import junitbook.ejb.domain.OrderLocal;

public class TestPetstoreEJB extends TestCase
{    
    private TestablePetstoreEJB petstore;
    private Mock mockOrderLocal;
    private OrderLocal orderLocal;

    protected void setUp()
    {
        petstore = new TestablePetstoreEJB();

        mockOrderLocal = new Mock(OrderLocal.class);
        orderLocal = (OrderLocal) mockOrderLocal.proxy();

        petstore.setupCreateOrderHelper(orderLocal);
        mockOrderLocal.matchAndReturn("getOrderId", 
            new Integer(1234));
    }

    protected void tearDown()
    {    
        mockOrderLocal.verify();        
    }
    
    public void testCreateOrderOk() throws Exception
    {       
        int orderId = petstore.createOrder(new Date(), "item1");

        assertEquals(1234, orderId);        
    }

    public void testCreateOrderJMSException() throws Exception
    {
        petstore.setupThrowOnSendToJMSQueueHelper(
            new JMSException("error"));

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