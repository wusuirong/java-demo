package junitbook.ejb.service;

import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import junitbook.ejb.domain.OrderLocal;
import junitbook.ejb.domain.OrderUtil;

public class OrderProcessorMDB 
    implements MessageDrivenBean, MessageListener
{
    public void onMessage(Message recvMsg)
    {
        ObjectMessage msg = (ObjectMessage) recvMsg;

        Integer orderId;
        try
        {
            orderId = (Integer) msg.getObject();
            OrderLocal order = OrderUtil.getOrder(orderId);
            proceedOrder(order);
        }
        catch (Exception e)
        {
            throw new EJBException("Error processing order...");
        }
    }

    private void proceedOrder(OrderLocal order) throws Exception
    {
        // Perform some business logic here and notify the customer
        // possibly by sending an email.
    }

    public void ejbCreate() {}
    public void setMessageDrivenContext(
        MessageDrivenContext context) {}
    public void ejbRemove() {}
}