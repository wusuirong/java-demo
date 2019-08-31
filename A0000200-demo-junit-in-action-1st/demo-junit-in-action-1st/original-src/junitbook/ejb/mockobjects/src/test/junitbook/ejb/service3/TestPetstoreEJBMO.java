package junitbook.ejb.service3;

import java.util.Date;

import javax.ejb.EJBException;
import javax.jms.JMSException;

import com.mockobjects.dynamic.Mock;
import com.mockobjects.jms.MockQueue;
import com.mockobjects.jms.MockQueueConnection;
import com.mockobjects.jms.MockQueueConnectionFactory;
import com.mockobjects.jms.MockQueueSender;
import com.mockobjects.jms.MockQueueSession;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junitbook.ejb.JNDITestSetupMO;
import junitbook.ejb.domain.OrderLocal;
import junitbook.ejb.domain.OrderLocalHome;
import junitbook.ejb.service.PetstoreEJB;

public class TestPetstoreEJBMO extends TestCase
{    
    private static JNDITestSetupMO jndiTestSetup;
    
    private PetstoreEJB petstore;
   
    private Mock mockOrderLocalHome;
    private OrderLocalHome orderLocalHome;
    private Mock mockOrderLocal;

    private MockQueue queue;
    private MockQueueConnectionFactory queueConnectionFactory;
    private MockQueueConnection queueConnection;
    private MockQueueSession queueSession;
    private MockQueueSender queueSender;
                    
    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestPetstoreEJBMO.class);
        suite.addTest(new TestPetstoreEJBMO("testCreateOrderOk"));
        suite.addTest(new TestPetstoreEJBMO("testCreateOrderJMSException"));
        jndiTestSetup = new JNDITestSetupMO(suite);
        return jndiTestSetup;
    }

    public TestPetstoreEJBMO(String testName)
    {
        super(testName);
    }

    protected void setUp() throws Exception
    {
        petstore = new PetstoreEJB() {};
        
        mockOrderLocalHome = new Mock(OrderLocalHome.class);
        orderLocalHome = (OrderLocalHome) mockOrderLocalHome.proxy(); 

        mockOrderLocal = new Mock(OrderLocal.class);
        OrderLocal orderLocal = (OrderLocal) mockOrderLocal.proxy(); 

        mockOrderLocalHome.matchAndReturn("create", orderLocal);

        queue = new MockQueue();
        queueConnectionFactory = new MockQueueConnectionFactory();
        queueConnection = new MockQueueConnection();
        queueSession = new MockQueueSession();
        queueSender = new MockQueueSender();

        queueConnection.setupQueueSession(queueSession);
        queueSession.setupSender(queueSender);       

        mockOrderLocal.matchAndReturn("getOrderId", 
            new Integer(1234));
    }

    public void testCreateOrderOk() throws Exception
    {
        jndiTestSetup.getContext().setupAddLookup(orderLocalHome);
        jndiTestSetup.getContext().setupAddLookup(queue);
        jndiTestSetup.getContext().setupAddLookup(queueConnectionFactory);

        queueConnectionFactory.setupQueueConnection(queueConnection);

        int orderId = petstore.createOrder(new Date(), "item1");
        
        assertEquals(1234, orderId);
    }

    public void testCreateOrderJMSException() throws Exception
    {
        jndiTestSetup.getContext().setupAddLookup(queue);
        jndiTestSetup.getContext().setupAddLookup(queueConnectionFactory);

        queueConnectionFactory.setupThrowException(new JMSException("error"));

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
