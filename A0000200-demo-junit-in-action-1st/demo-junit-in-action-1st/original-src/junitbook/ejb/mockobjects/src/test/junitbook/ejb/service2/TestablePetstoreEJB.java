package junitbook.ejb.service2;

import java.io.Serializable;
import java.util.Date;

import javax.jms.JMSException;
import javax.naming.NamingException;

import junitbook.ejb.domain.OrderLocal;

public class TestablePetstoreEJB extends PetstoreEJB
{
    private OrderLocal orderLocal;
    private JMSException jmsExceptionToThrow;
    private NamingException namingExceptionToThrow;
    
    public void setupCreateOrderHelper(OrderLocal orderLocal)
    {
        this.orderLocal = orderLocal;
    }
    
    protected OrderLocal createOrderHelper(Date orderDate, 
        String orderItem)
    {
        return this.orderLocal; 
    }

    public void setupThrowOnSendToJMSQueueHelper(
        JMSException exception)
    {
        this.jmsExceptionToThrow = exception;
    }

    public void setupThrowOnSendToJMSQueueHelper(
        NamingException exception)
    {
        this.namingExceptionToThrow = exception;
    }

    protected void sendToJMSQueueHelper(String queueName, 
        Serializable object, boolean transacted) 
        throws NamingException, JMSException
    {
        if (this.jmsExceptionToThrow != null)
        {
            throw this.jmsExceptionToThrow; 
        }
        if (this.namingExceptionToThrow != null)
        {
            throw this.namingExceptionToThrow;
        }
    }
}