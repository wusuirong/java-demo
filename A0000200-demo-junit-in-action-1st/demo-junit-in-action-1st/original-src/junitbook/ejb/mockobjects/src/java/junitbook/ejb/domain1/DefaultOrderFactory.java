package junitbook.ejb.domain1;

import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.rmi.PortableRemoteObject;

import junitbook.ejb.domain.OrderLocal;
import junitbook.ejb.domain.OrderLocalHome;
import junitbook.ejb.util.JNDINames;
import junitbook.ejb.util.JNDIUtil;

public class DefaultOrderFactory implements OrderFactory
{
    private static OrderLocalHome orderLocalHome;

    protected OrderLocalHome getOrderHome()
    {
        if (orderLocalHome == null)
        {
            Object obj = 
                JNDIUtil.lookup(JNDINames.ORDER_LOCALHOME);
            orderLocalHome = 
                (OrderLocalHome) PortableRemoteObject.narrow(
                    obj, OrderLocalHome.class);
        }
        return orderLocalHome;        
    }
    
    public OrderLocal createOrder(Date orderDate, String orderItem)
    {
        try
        {
            return getOrderHome().create(orderDate, orderItem);
        }
        catch (CreateException e)
        {
            throw new RuntimeException("Failed to create ["
                    + OrderLocal.class.getName() + "]");
        }
    }

    public OrderLocal getOrder(Integer orderId)
    {
        try
        {
            return getOrderHome().findByPrimaryKey(orderId);
        }
        catch (FinderException e)
        {
            throw new RuntimeException("Failed to find Order "
                + "bean for id [" + orderId + "]");
        }
    }

}
