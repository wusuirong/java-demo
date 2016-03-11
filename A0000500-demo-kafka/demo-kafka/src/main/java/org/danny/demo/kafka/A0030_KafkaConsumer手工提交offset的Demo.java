package org.danny.demo.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * 
 * @author wusuirong
 *
 * @Description 由consumer每隔一段时间自动commit offset不能满足某些应用要求，
 *              比如应用是收集一批record后再提交到数据库
 *              ，如果offset为100-200的record批量入库时失败，这时定时自动commit机制可能把offset提交到150，
 *              导致下次应用恢复时会丢失offset为100-149的record。 所以可以使用手工commit的api去消费record。
 *
 * @Email wusuirong@xxx.com
 *
 *        www.xxx.com Copyright (c) 2014 All Rights Reserved.
 */
public class A0030_KafkaConsumer手工提交offset的Demo {

	public static void main(String[] args) {
		Properties props = new Properties();
	     props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.11.133:9092");
	     props.put(ConsumerConfig.GROUP_ID_CONFIG, "demoConsumer1");
	     props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
	     props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
	     props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
	     props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
	     consumer.subscribe(Arrays.asList("TOPIC_TEST"));
	     final int minBatchSize = 5;
	     List<ConsumerRecord<String, String>> buffer = new ArrayList<ConsumerRecord<String, String>>();
	     while (true) {
	         ConsumerRecords<String, String> records = consumer.poll(100);
	         for (ConsumerRecord<String, String> record : records) {
	             buffer.add(record);
	         }
	         if (buffer.size() >= minBatchSize) {
	             insertIntoDb(buffer);
	             
	             /*
	              * 提交kafka中记录的commit position
	              * 如果要保存到zookeeper或别的地方就不要用这个api
	              */
	             consumer.commitSync();
	             buffer.clear();
	         }
	     }
	}

	private static void insertIntoDb(List<ConsumerRecord<String, String>> buffer) {
		System.out.println("record入库");
	}
}
