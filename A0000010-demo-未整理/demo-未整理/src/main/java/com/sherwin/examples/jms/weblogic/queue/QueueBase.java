package com.sherwin.examples.jms.weblogic.queue;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;

public class QueueBase {
	protected QueueConnectionFactory queueConnectionFactory;
	protected QueueConnection queueConnection;
	protected QueueSession queueSession;
	protected Queue queue;

	public QueueBase(Context context) {
		try {
			String jmsFactory = Props.get("jms.factory");
			queueConnectionFactory = (QueueConnectionFactory) context.lookup(jmsFactory);
			queueConnection = queueConnectionFactory.createQueueConnection();
			queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			String queueName = Props.get("queue.name");
			queue = (Queue) context.lookup(queueName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}