package com.sherwin.examples.jms.weblogic.topic;

import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.naming.Context;

public class TopicManager {
	protected TopicConnectionFactory connectionFactory;
	protected TopicConnection connection;
	protected TopicSession session;
	protected Topic topic;

	public TopicManager(Context context) {
		try {
			String jmsFactory = Props.get("jms.factory.for.topic");
			connectionFactory = (TopicConnectionFactory) context.lookup(jmsFactory);
			connection = connectionFactory.createTopicConnection();
			session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			String queueName = Props.get("topic.name");
			topic = (Topic) context.lookup(queueName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}