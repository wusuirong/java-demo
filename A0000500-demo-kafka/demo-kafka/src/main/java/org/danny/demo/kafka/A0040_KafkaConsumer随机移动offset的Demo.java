package org.danny.demo.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

/**
 * 
 * @author wusuirong
 *
 * 
 * 
 * @Email wusuirong@xxx.com
 *
 *        www.xxx.com Copyright (c) 2014 All Rights Reserved.
 */
public class A0040_KafkaConsumer随机移动offset的Demo {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.11.133:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "demoConsumer");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList("TOPIC_TEST"));

		try {
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(10);
				for (TopicPartition partition : records.partitions()) {
					List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
					for (ConsumerRecord<String, String> record : partitionRecords) {
						System.out.println(record.offset() + ": " + record.value());
					}
					long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
					/*
					 * 更新kafka中的committed position，record的offset下标从0开始，而committed position记录的是已经消费的数量，
					 * 所以如果消费了5条record，lastOffset=4，而commited position应该是5，所以要做lastOffset+1的操作。
					 */
					consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
				}
			}
		} finally {
			consumer.close();
		}
	}

}
