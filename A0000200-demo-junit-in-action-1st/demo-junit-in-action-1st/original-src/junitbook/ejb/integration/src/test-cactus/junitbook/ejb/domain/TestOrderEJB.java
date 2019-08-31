package junitbook.ejb.domain;

import java.util.Date;

import javax.naming.InitialContext;

import org.apache.cactus.ServletTestCase;

import junitbook.ejb.util.JNDINames;

public class TestOrderEJB extends ServletTestCase
{
    public void testEjbCreateOk() throws Exception
    {
        OrderLocalHome orderHome = 
            (OrderLocalHome) new InitialContext().lookup(
                JNDINames.ORDER_LOCALHOME); 

        Date date = new Date();
        String item = "item 1";

        OrderLocal order = orderHome.create(date, item);

        assertEquals(date, order.getOrderDate());
        assertEquals(item, order.getOrderItem());
        assertEquals(new Integer(date.hashCode() 
            + item.hashCode()), order.getOrderId());
    }
}
