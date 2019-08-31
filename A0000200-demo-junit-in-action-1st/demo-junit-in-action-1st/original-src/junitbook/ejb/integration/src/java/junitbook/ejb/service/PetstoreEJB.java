package junitbook.ejb.service;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import junitbook.ejb.domain.OrderLocal;
import junitbook.ejb.domain.OrderUtil;
import junitbook.ejb.util.JMSUtil;
import junitbook.ejb.util.JNDINames;

public class PetstoreEJB implements SessionBean
{
    public int createOrder(Date orderDate, String orderItem)       
        throws RemoteException
    {
        OrderLocal order = OrderUtil.createOrder(orderDate, 
            orderItem);

        try
        {
            JMSUtil.sendToJMSQueue(JNDINames.QUEUE_ORDER, 
                order.getOrderId(), false);
        }
        catch (Exception e)
        {
            throw new EJBException(e);
        }
        return order.getOrderId().intValue();
    }

    public void ejbCreate() 
        throws CreateException, RemoteException {}
    public void setSessionContext(SessionContext sessionContext) 
        throws EJBException, RemoteException {}
    public void ejbRemove() 
        throws EJBException, RemoteException {}
    public void ejbActivate() 
        throws EJBException, RemoteException {}
    public void ejbPassivate() 
        throws EJBException, RemoteException {}
}
