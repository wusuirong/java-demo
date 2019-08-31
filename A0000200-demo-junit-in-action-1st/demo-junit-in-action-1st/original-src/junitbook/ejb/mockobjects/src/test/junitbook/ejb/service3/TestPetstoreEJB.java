package junitbook.ejb.service3;

import java.util.Date;

import javax.ejb.EJBException;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;

import com.mockobjects.dynamic.C;
import com.mockobjects.dynamic.Mock;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junitbook.ejb.JNDITestSetup;
import junitbook.ejb.domain.OrderLocal;
import junitbook.ejb.domain.OrderLocalHome;
import junitbook.ejb.service.PetstoreEJB;
import junitbook.ejb.util.JNDINames;

public class TestPetstoreEJB extends TestCase
{    
    private static JNDITestSetup jndiTestSetup;
    
    private PetstoreEJB petstore;
   
    private Mock mockOrderLocalHome;
    private OrderLocalHome orderLocalHome;
    private Mock mockOrderLocal;
    private Mock mockQueue;
    private Queue queue;
    private Mock mockQueueConnectionFactory;
    private QueueConnectionFactory queueConnectionFactory;
    private Mock mockQueueConnection;
    private QueueConnection queueConnection;
    private Mock mockQueueSession;
    private Mock mockQueueSender;
    private Mock mockObjectMessage;
                    
    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestPetstoreEJB.class);
        jndiTestSetup = new JNDITestSetup(suite);
        return jndiTestSetup;
    }

    protected void setUp() throws Exception
    {
        petstore = new PetstoreEJB() {};

        jndiTestSetup.getMockContext().reset();
        
        setUpOrderMocks();
        setUpJMSMocks();
        setUpJNDILookups();                

        jndiTestSetup.getMockContext().matchAndReturn("close", 
            null);
    }

    public void setUpOrderMocks()
    {
        mockOrderLocalHome = new Mock(OrderLocalHome.class);
        orderLocalHome = 
            (OrderLocalHome) mockOrderLocalHome.proxy(); 

        mockOrderLocal = new Mock(OrderLocal.class);
        OrderLocal orderLocal = (OrderLocal) mockOrderLocal.proxy(); 

        mockOrderLocalHome.matchAndReturn("create", C.ANY_ARGS, 
            orderLocal);
        mockOrderLocal.matchAndReturn("getOrderId", 
            new Integer(1234));
    }

    public void setUpJMSMocks()
    {
        mockQueue = new Mock(Queue.class);
        queue = (Queue) mockQueue.proxy(); 

        mockQueueConnectionFactory = 
            new Mock(QueueConnectionFactory.class);
        queueConnectionFactory = (QueueConnectionFactory) 
            mockQueueConnectionFactory.proxy(); 

        mockQueueConnection = new Mock(QueueConnection.class);
        queueConnection = 
            (QueueConnection) mockQueueConnection.proxy(); 
        mockQueueConnection.matchAndReturn("close", null);

        mockObjectMessage = new Mock(ObjectMessage.class);
        ObjectMessage objectMessage = 
            (ObjectMessage) mockObjectMessage.proxy();
        
        mockQueueSession = new Mock(QueueSession.class);
        QueueSession queueSession = 
            (QueueSession) mockQueueSession.proxy(); 
        mockQueueSession.matchAndReturn("close", null);
        mockQueueSession.matchAndReturn("createObjectMessage",
            C.ANY_ARGS, objectMessage);

        mockQueueConnection.matchAndReturn("createQueueSession",
            C.ANY_ARGS, queueSession);

        mockQueueSender = new Mock(QueueSender.class);
        QueueSender queueSender = 
            (QueueSender) mockQueueSender.proxy(); 
        mockQueueSender.matchAndReturn("close", null);
        mockQueueSender.matchAndReturn("send", C.ANY_ARGS, null);

        mockQueueSession.matchAndReturn("createSender", 
            C.ANY_ARGS, queueSender);       
    }

    public void setUpJNDILookups()
    {
        jndiTestSetup.getMockContext().matchAndReturn(
            "lookup", JNDINames.ORDER_LOCALHOME, orderLocalHome);
        jndiTestSetup.getMockContext().matchAndReturn(
            "lookup", JNDINames.QUEUE_ORDER, queue);
        jndiTestSetup.getMockContext().matchAndReturn(
            "lookup", JNDINames.QUEUE_CONNECTION_FACTORY, 
            queueConnectionFactory);
    }

    protected void tearDown()
    {
        jndiTestSetup.getMockContext().verify();
        mockOrderLocal.verify();
        mockOrderLocalHome.verify();
        mockQueue.verify();
        mockQueueConnection.verify();
        mockQueueConnectionFactory.verify();
        mockQueueSender.verify();
        mockQueueSession.verify();
        mockObjectMessage.verify();        
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
