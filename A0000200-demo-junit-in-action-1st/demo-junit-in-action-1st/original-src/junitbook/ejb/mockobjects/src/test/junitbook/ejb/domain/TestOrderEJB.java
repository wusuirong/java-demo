package junitbook.ejb.domain;

import java.util.Date;

import junit.framework.TestCase;

public class TestOrderEJB extends TestCase
{       
    public class TestableOrderEJB extends OrderEJB
    {
        private Integer orderId;
        private String item;
        private Date date;
        
        public Integer getOrderId()
            { return this.orderId; }
        public void setOrderId(Integer orderId) 
            { this.orderId = orderId; }
        public Date getOrderDate() 
            { return this.date; }
        public void setOrderDate(Date orderDate) 
            { this.date = orderDate; }
        public String getOrderItem() 
            { return this.item; }
        public void setOrderItem(String item) 
            { this.item = item; }
    }

    public void testEjbCreateOk() throws Exception
    {
        TestableOrderEJB order = new TestableOrderEJB();

        Date date = new Date();
        String item = "item 1";

        order.ejbCreate(date, item);

        assertEquals(order.getOrderDate().hashCode() 
            + order.getOrderItem().hashCode(), 
            order.getOrderId().intValue()); 
        assertEquals(date, order.getOrderDate());
        assertEquals(item, order.getOrderItem());
    }
}