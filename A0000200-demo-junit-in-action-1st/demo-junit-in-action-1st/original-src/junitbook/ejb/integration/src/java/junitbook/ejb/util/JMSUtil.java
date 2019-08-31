package junitbook.ejb.util;

import java.io.Serializable;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSUtil
{
    public static void sendToJMSQueue(String queueName, 
        Serializable obj, boolean transacted) 
        throws NamingException, JMSException
    {
        InitialContext ic = null;
        QueueConnection cnn = null;
        QueueSender sender = null;
        QueueSession session = null;

        try
        {
            ic = new InitialContext();

            Queue queue = (Queue) ic.lookup(queueName);

            QueueConnectionFactory factory = 
                (QueueConnectionFactory) ic.lookup(
                JNDINames.QUEUE_CONNECTION_FACTORY);
            cnn = factory.createQueueConnection();
            session = cnn.createQueueSession(transacted, 
                QueueSession.AUTO_ACKNOWLEDGE);

            ObjectMessage msg = session.createObjectMessage(obj);

            sender = session.createSender(queue);
            sender.send(msg);
        }
        finally
        {
            if (sender != null)
            {
                sender.close();
            }
            if (session != null)
            {
                session.close();
            }
            if (cnn != null)
            {
                cnn.close();
            }
            if (ic != null)
            {
                ic.close();
            }
        }
    }
}
