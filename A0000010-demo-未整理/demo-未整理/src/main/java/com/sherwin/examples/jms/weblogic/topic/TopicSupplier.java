package com.sherwin.examples.jms.weblogic.topic;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.TopicPublisher;
import javax.naming.Context;

public class TopicSupplier extends TopicManager {
	private TopicPublisher publisher;

	public TopicSupplier(Context context) {
		super(context);

		try {
			publisher = session.createPublisher(topic);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void sendMsg(String msg) throws JMSException {
		TextMessage txtMsg = session.createTextMessage();
		txtMsg.setText(msg);

		connection.start();
		publisher.publish(txtMsg);
	}

	public void close() throws JMSException {
		publisher.close();
		session.close();
		connection.close();
	}

	public static void main(String[] args) {
		Context context = Props.getInitialContext();
		TopicSupplier queueSupplier = new TopicSupplier(context);

		try {
			queueSupplier.sendMsg("你好主题！");

			System.out.println("A message have been sent!");
		} catch (JMSException ex) {
			ex.printStackTrace();
		} finally {
			try {
				queueSupplier.close();
			} catch (JMSException ex) {
				ex.printStackTrace();
			}
		}
	}
}