package junitbook.ejb.service1;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import junitbook.ejb.domain.OrderLocal;
import junitbook.ejb.domain1.DefaultOrderFactory;
import junitbook.ejb.domain1.OrderFactory;
import junitbook.ejb.util.JNDINames;
import junitbook.ejb.util1.DefaultJMSUtil;
import junitbook.ejb.util1.JMSUtil;

public abstract class PetstoreEJB implements SessionBean
{
    private static OrderFactory orderFactory = 
        new DefaultOrderFactory();     
    private static JMSUtil jmsUtil = new DefaultJMSUtil();

    public int createOrder(Date orderDate, String orderItem)
    {
        OrderLocal order = orderFactory.createOrder(orderDate, 
            orderItem);

        try
        {
            jmsUtil.sendToJMSQueue(JNDINames.QUEUE_ORDER, 
                order.getOrderId(), false);
        }
        catch (Exception e)
        {
            throw new EJBException(e);
        }
        return order.getOrderId().intValue();
    }

    protected void setOrderFactory(OrderFactory util)
    {
        orderFactory = util;
    }

    protected void setJMSUtil(JMSUtil util)
    {
        jmsUtil = util;
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
