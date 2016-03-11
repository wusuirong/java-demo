package com.sherwin.examples.jms.weblogic.queue;

import javax.jms.JMSException;
import javax.jms.QueueSender;
import javax.jms.TextMessage;
import javax.naming.Context;

public class QueueSupplier extends QueueBase {
	private QueueSender queueSender;

	public QueueSupplier(Context context) {
		super(context);

		try {
			queueSender = queueSession.createSender(queue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void sendMsg(String msg) throws JMSException {
		TextMessage txtMsg = queueSession.createTextMessage();
		txtMsg.setText(msg);

		queueConnection.start();
		queueSender.send(txtMsg);
	}

	public void close() throws JMSException {
		queueSender.close();
		queueSession.close();
		queueConnection.close();
	}

	public static void main(String[] args) {
		Context context = Props.getInitialContext();
		QueueSupplier queueSupplier = new QueueSupplier(context);

		try {
			queueSupplier.sendMsg("Hello World");

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