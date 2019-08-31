package junitbook.ejb.service1;

import java.util.Date;

import javax.ejb.EJBException;
import javax.jms.JMSException;

import com.mockobjects.dynamic.C;
import com.mockobjects.dynamic.Mock;

import junit.framework.TestCase;
import junitbook.ejb.domain.OrderLocal;
import junitbook.ejb.domain1.OrderFactory;
import junitbook.ejb.util1.JMSUtil;

public class TestPetstoreEJB extends TestCase
{    
    private PetstoreEJB petstore;
    private Mock mockOrderFactory;
    private Mock mockJMSUtil;
    private OrderFactory orderFactory;
    private JMSUtil jmsUtil;

    private Mock mockOrderLocal;
    private OrderLocal orderLocal;
    
    protected void setUp()
    {
        petstore = new PetstoreEJB() {};

        mockOrderLocal = new Mock(OrderLocal.class);
        orderLocal = (OrderLocal) mockOrderLocal.proxy();
        
        mockOrderFactory = new Mock(OrderFactory.class);
        orderFactory = (OrderFactory) mockOrderFactory.proxy();
                
        mockJMSUtil = new Mock(JMSUtil.class);
        jmsUtil = (JMSUtil) mockJMSUtil.proxy();

        petstore.setOrderFactory(orderFactory);
        petstore.setJMSUtil(jmsUtil);

        mockOrderFactory.expectAndReturn("createOrder", 
            C.args(C.IS_ANYTHING, C.IS_ANYTHING), orderLocal); 
        mockOrderLocal.matchAndReturn("getOrderId", 
            new Integer(1234));
    }

    protected void tearDown()
    {
        mockJMSUtil.verify();
        mockOrderFactory.verify();
        mockOrderLocal.verify();            
    }
    
    public void testCreateOrderOk() throws Exception
    {
        mockJMSUtil.expect("sendToJMSQueue",
            C.args(C.IS_ANYTHING, C.eq(new Integer(1234)), 
                C.IS_ANYTHING));
        
        int orderId = petstore.createOrder(new Date(), "item1");
        
        assertEquals(1234, orderId);
    }

    public void testCreateOrderJMSException() throws Exception
    {
        mockJMSUtil.expectAndThrow("sendToJMSQueue",
            C.args(C.IS_ANYTHING, C.eq(new Integer(1234)), 
                C.IS_ANYTHING), new JMSException("error"));

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