package org.danny.demo.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class A0010_KafkaProducer同步发送Demo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.11.133:9092");
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "demoProducer");
		props.put(ProducerConfig.ACKS_CONFIG, "all"); // all表示要等到record完全提交才结束，最慢最安全。
		props.put(ProducerConfig.RETRIES_CONFIG, 0); // 调用send时，send内部发送失败重试的次数。
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 5000); // buffer中所有record占用字节数超过此配置时，就要发送，好比公交车上满了就可以发车了，不用等到发车时间。
		props.put(ProducerConfig.LINGER_MS_CONFIG, 100); // 延迟发送时间，当buffer存入record后，如果超过此时间没有新record加入，则发送buffer的record，避免buffer一直不满导致record不发送。好比公交车到点就要发车，即使没坐满。
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); // buffer内存大小上限，如果buffer满了，会导致send方法阻塞。设置block.on.buffer.full=false可以让send方法在buffer满时不阻塞，改为抛异常。好比公交车站的大小，人满了就进不去了。
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		System.out.println("初始化producer完毕");
		for (int i = 0; i < 100; i++) {
			/*
			 * Producer内部有一个buffer，
			 * 调用send方法时只是把record放入buffer，然后立即返回，
			 * 后台Producer会批量发送record，这样性能会高。
			 */
			String key = "key" + Integer.toString(i);
			String value = "value" + Integer.toString(i);
			ProducerRecord<String, String> record = new ProducerRecord<String, String>("TOPIC_TEST", key, value);
			Future<RecordMetadata> future = producer.send(record);
			
			/*
			 * 这是阻塞调用，等到底层发送完才返回
			 * 底层什么时候发送record取决于配置的batch size和linger
			 */
			RecordMetadata result = future.get();
			System.out.println("发送完毕：" + future.isDone());
			System.out.printf("topic: %s, partition: %d, offset: %d\n", result.topic(), result.partition(), result.offset());
			
			System.out.println("发送数据" + i);
		}

		// 因为内部有buffer，如果不调用close有可能丢record。
		producer.close();
		
		System.out.println("发送完成");
	}
}
