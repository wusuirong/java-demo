package junitbook.ejb;

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
import junitbook.ejb.domain.OrderLocal;
import junitbook.ejb.domain.OrderLocalHome;
import junitbook.ejb.util.JNDINames;

public class CommonPetstoreTestCase extends TestCase
{    
    protected static JNDITestSetup jndiTestSetup;
    
    protected Mock mockOrderLocalHome;
    protected OrderLocalHome orderLocalHome;
    protected Mock mockOrderLocal;
    protected Mock mockQueue;
    protected Queue queue;
    protected Mock mockQueueConnectionFactory;
    protected QueueConnectionFactory queueConnectionFactory;
    protected Mock mockQueueConnection;
    protected QueueConnection queueConnection;
    protected Mock mockQueueSession;
    protected Mock mockQueueSender;
    protected Mock mockObjectMessage;
                    
    public static Test suite(Class testClass)
    {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(testClass);
        jndiTestSetup = new JNDITestSetup(suite);
        return jndiTestSetup;
    }

    protected void setUp() throws Exception
    {
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
        orderLocalHome = (OrderLocalHome) mockOrderLocalHome.proxy(); 

        mockOrderLocal = new Mock(OrderLocal.class);
        OrderLocal orderLocal = (OrderLocal) mockOrderLocal.proxy(); 

        mockOrderLocalHome.matchAndReturn("create", C.ANY_ARGS, 
            orderLocal);
        mockOrderLocalHome.matchAndReturn("findByPrimaryKey",
            new Integer(1234), orderLocal);
        mockOrderLocal.matchAndReturn("getOrderId", 
            new Integer(1234));
    }

    public void setUpJMSMocks()
    {
        mockQueue = new Mock(Queue.class);
        queue = (Queue) mockQueue.proxy(); 

        mockQueueConnectionFactory = 
            new Mock(QueueConnectionFactory.class);
        queueConnectionFactory = 
            (QueueConnectionFactory) mockQueueConnectionFactory.proxy(); 

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
}
