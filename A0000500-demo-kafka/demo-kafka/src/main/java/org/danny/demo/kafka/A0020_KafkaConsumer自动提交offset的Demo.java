package org.danny.demo.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 *             
 * @author wusuirong
 *
 * @Description 
 * 由consumer控制offset的commit
 * consumer会每隔一段时间commit一次
 *
 * @Email wusuirong@xxx.com
 *
 * www.xxx.com
 * Copyright (c) 2014 All Rights Reserved.
 */
public class A0020_KafkaConsumer自动提交offset的Demo {

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
		
		consumer.subscribe(Arrays.asList("TOPIC_TEST"));
		System.out.println("consumer订阅topic成功");
		
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
			}
		}
		
//		consumer.close();
	}
}
