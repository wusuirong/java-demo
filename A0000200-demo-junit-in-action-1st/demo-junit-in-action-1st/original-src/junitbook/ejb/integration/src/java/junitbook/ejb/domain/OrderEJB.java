package junitbook.ejb.domain;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

public abstract class OrderEJB implements EntityBean
{
    public abstract Integer getOrderId();
    public abstract void setOrderId(Integer orderId);
    public abstract Date getOrderDate();
    public abstract void setOrderDate(Date orderDate);
    public abstract String getOrderItem();
    public abstract void setOrderItem(String item);

    public Integer ejbCreate(Date orderDate, String orderItem)
        throws CreateException
    {
        int uid = 0;

        // Note: Would need a real counter here. This is a hack!
        uid = orderDate.hashCode() + orderItem.hashCode();
        
        setOrderId(new Integer(uid));
        setOrderDate(orderDate);
        setOrderItem(orderItem);
        
        return new Integer(uid);
    }

    public void ejbPostCreate(Date orderDate, String orderItem)
        throws CreateException {}
    public void ejbActivate() 
        throws EJBException, RemoteException {}
    public void ejbLoad() 
        throws EJBException, RemoteException {}
    public void ejbPassivate() 
        throws EJBException, RemoteException {}
    public void ejbRemove() 
        throws RemoveException, EJBException, RemoteException {}
    public void ejbStore() 
        throws EJBException, RemoteException {}
    public void setEntityContext(EntityContext context) 
        throws EJBException, RemoteException {}
    public void unsetEntityContext() 
        throws EJBException, RemoteException {}
}
