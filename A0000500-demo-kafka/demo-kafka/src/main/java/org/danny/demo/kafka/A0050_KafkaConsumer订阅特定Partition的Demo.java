package org.danny.demo.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

/**
 * 
 * @author wusuirong
 *
 * @Description
 * 如果不想使用kafka的load balance功能，则可以手工订阅Partition。
 * 比如Consumer消费数据和Partition有关，或Consumer有自动恢复功能，宕机不需要kafka做load balance。
 *
 * @Email wusuirong@xxx.com
 *
 *        www.xxx.com Copyright (c) 2014 All Rights Reserved.
 */
public class A0050_KafkaConsumer订阅特定Partition的Demo {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.11.133:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "demoConsumer");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true"); // 此配置设置consumer自动提交offset
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000"); // 此配置定义了consumer每隔多长时间commit一次offset
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000"); // consumer会定时发送心跳给server，超过此时间则server认为consumer宕机。
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		System.out.println("consumer初始化完毕");

		String topic = "TOPIC_TEST";
		TopicPartition partition0 = new TopicPartition(topic, 0);
//		TopicPartition partition1 = new TopicPartition(topic, 1);
		consumer.assign(Arrays.asList(partition0));

		System.out.println("consumer订阅topic成功");

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
			}
		}

		// consumer.close();
	}
}
