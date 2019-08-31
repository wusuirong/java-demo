package junitbook.ejb.domain1;

import java.util.Date;

import junitbook.ejb.domain.OrderLocal;

public interface OrderFactory
{
    OrderLocal createOrder(Date orderDate, String orderItem);
    OrderLocal getOrder(Integer orderId);
}
