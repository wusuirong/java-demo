package junitbook.ejb.domain;

import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface OrderLocalHome extends EJBLocalHome
{
    OrderLocal create(Date orderDate, String orderItem) 
        throws CreateException;
    OrderLocal findByPrimaryKey(Integer orderId) 
        throws FinderException;
}
