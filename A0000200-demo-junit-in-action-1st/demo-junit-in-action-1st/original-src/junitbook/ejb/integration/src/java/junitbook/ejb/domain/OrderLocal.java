package junitbook.ejb.domain;

import java.util.Date;

import javax.ejb.EJBLocalObject;

public interface OrderLocal extends EJBLocalObject
{
    Integer getOrderId();
    void setOrderId(Integer orderUId);
    Date getOrderDate();
    void setOrderDate(Date orderDate);
    String getOrderItem();
    void setOrderItem(String item);
}
