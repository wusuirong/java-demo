package junitbook.ejb.service2;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.jms.JMSException;
import javax.naming.NamingException;

import junitbook.ejb.domain.OrderLocal;
import junitbook.ejb.domain.OrderUtil;
import junitbook.ejb.util.JMSUtil;
import junitbook.ejb.util.JNDINames;

public abstract class PetstoreEJB implements SessionBean
{
    public int createOrder(Date orderDate, String orderItem)
    {
        OrderLocal order = createOrderHelper(orderDate, orderItem);

        try
        {
            sendToJMSQueueHelper(JNDINames.QUEUE_ORDER,
                order.getOrderId(), false);
        }
        catch (Exception e)
        {
            throw new EJBException(e);
        }

        return order.getOrderId().intValue();
    }

    protected OrderLocal createOrderHelper(Date orderDate, 
        String orderItem)
    {
        return OrderUtil.createOrder(orderDate, orderItem);
    }

    protected void sendToJMSQueueHelper(String queueName, 
        Serializable object, boolean transacted) 
        throws NamingException, JMSException
    {
        JMSUtil.sendToJMSQueue(queueName, object, transacted); 
    }
    
    public void setSessionContext(SessionContext sessionContext) 
        throws EJBException, RemoteException {}
    public void ejbRemove() 
        throws EJBException, RemoteException {}
    public void ejbActivate() 
        throws EJBException, RemoteException {}
    public void ejbPassivate() 
        throws EJBException, RemoteException {}
}
