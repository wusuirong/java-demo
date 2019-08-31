package junitbook.ejb.service3;

import javax.jms.ObjectMessage;

import com.mockobjects.dynamic.Mock;

import junit.framework.Test;
import junitbook.ejb.CommonPetstoreTestCase;
import junitbook.ejb.service.OrderProcessorMDB;

public class TestOrderProcessorMDB extends CommonPetstoreTestCase
{       
    private OrderProcessorMDB orderProcessor;

    public static Test suite()
    {
        return suite(TestOrderProcessorMDB.class);
    } 

    protected void setUp() throws Exception
    {
        super.setUp();
        
        orderProcessor = new OrderProcessorMDB();
    }

    public void testOnMessageOk() throws Exception
    {
        Mock mockMessage = new Mock(ObjectMessage.class);
        ObjectMessage message = 
            (ObjectMessage) mockMessage.proxy();

        mockMessage.expectAndReturn("getObject", 
            new Integer(1234));

        orderProcessor.onMessage(message);

        mockMessage.verify();
    }
}
