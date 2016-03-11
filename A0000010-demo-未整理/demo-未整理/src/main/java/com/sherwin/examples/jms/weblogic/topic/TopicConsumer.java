package com.sherwin.examples.jms.weblogic.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.TopicSubscriber;
import javax.naming.Context;

public class TopicConsumer extends TopicManager implements MessageListener {
	private TopicSubscriber subscriber;

	public TopicConsumer(Context context) {
		super(context);

		try {
			subscriber = session.createSubscriber(topic);
			subscriber.setMessageListener(this);
			connection.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage txtmsg = (TextMessage) message;

			try {
				System.out.println("收到文本消息:" + txtmsg.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() throws JMSException {
		subscriber.close();
		session.close();
		connection.close();
	}

	public static void main(String[] args) {
		Context context = Props.getInitialContext();
		TopicConsumer queueComsumer = new TopicConsumer(context);

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