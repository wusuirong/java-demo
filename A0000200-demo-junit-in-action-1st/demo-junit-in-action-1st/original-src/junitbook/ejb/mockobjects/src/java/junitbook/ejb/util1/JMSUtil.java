package junitbook.ejb.util1;

import java.io.Serializable;
import javax.jms.JMSException;
import javax.naming.NamingException;

public interface JMSUtil
{
    void sendToJMSQueue(String queueName, Serializable obj,
    boolean transacted) throws NamingException, JMSException;
}
