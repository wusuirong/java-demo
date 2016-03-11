package com.sherwin.examples.jms.weblogic.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueReceiver;
import javax.jms.TextMessage;
import javax.naming.Context;

public class QueueComsumer extends QueueBase implements MessageListener {
	private QueueReceiver queueReceiver;

	public QueueComsumer(Context context) {
		super(context);

		try {
			queueReceiver = queueSession.createReceiver(queue);
			queueReceiver.setMessageListener(this);
			queueConnection.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage txtmsg = (TextMessage) message;

			try {
				System.out.println("I have received the TextMassage:" + txtmsg.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() throws JMSException {
		queueReceiver.close();
		queueSession.close();
		queueConnection.close();
	}

	public static void main(String[] args) {
		Context context = Props.getInitialContext();
		QueueComsumer queueComsumer = new QueueComsumer(context);

		synchronized (queueComsumer) {
			while (true) {
				try {
					queueComsumer.wait();
				} catch (InterruptedException interruptedException) {
					interruptedException.printStackTrace();

					try {
						queueComsumer.close();
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}