package junitbook.ejb.domain;

import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.rmi.PortableRemoteObject;

import junitbook.ejb.util.JNDINames;
import junitbook.ejb.util.JNDIUtil;

public class OrderUtil
{
    private static OrderLocalHome orderLocalHome;

    protected static void clearCache()
    {
        orderLocalHome = null;
    }

    protected static OrderLocalHome getOrderHome()
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
    
    public static OrderLocal createOrder(Date orderDate, 
        String orderItem)
    {
        try
        {
            return getOrderHome().create(orderDate, orderItem);
        }
        catch (CreateException e)
        {
            throw new RuntimeException("Failed to create ["
                + OrderLocal.class.getName() + "]. Reason ["
                + e.getMessage() + "]");
        }
    }

    public static OrderLocal getOrder(Integer orderId)
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