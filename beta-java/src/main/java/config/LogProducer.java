/**
 * Project Name:pomp-platform-logstash
 * File Name:LogProducer.java
 * Package Name:com.bocsoft.bocebiz.pomp.platform.service
 * Date:2017年4月25日上午10:26:17
 * Copyright (c) 2017, 开发七部 All Rights Reserved.
 */

package config;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:LogProducer <br/>
 * Date: 2017年4月25日 上午10:26:17 <br/>
 *
 * @author st-xsf6626
 * @see
 * @since JDK 1.6
 */
public class LogProducer implements Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogProducer.class);
    private KafkaProducer<String, String> kafkaProducer;
    private static final String[] XML_CONFIG_FILES = {"biz-service.xml"};
    static ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext(XML_CONFIG_FILES);

    public LogProducer(Properties producerConfig) {
        this.kafkaProducer = new KafkaProducer<>(producerConfig);
    }

    public static void main(String[] args) throws InterruptedException {
        PropertiesBean propertiesBean = (PropertiesBean) springContext.getBean("propertiesBean");
        Properties producerConfig = propertiesBean.getProducerConfig();
        Producer producer = new LogProducer(producerConfig);
        int i = 1;
        while (true) {
            producer.send("TestTopic", "test", "第" + i + "条数据");
            System.out.println("发送了" + i++ + "条");
            TimeUnit.SECONDS.sleep(1);
            if (i == 1000) {
                LOGGER.info("发了1000条数据");
                break;
            }
        }
    }

    @Override
    public Future<RecordMetadata> send(String topic, String key, String data) {
        return kafkaProducer.send(new ProducerRecord<String, String>(topic, key, data));
    }

    @Override
    public Boolean sendSync(String topic, String key, String data) {
        try {
            Future<RecordMetadata> rv = kafkaProducer.send(new ProducerRecord<String, String>(topic, key, data), null);
            kafkaProducer.flush();
            RecordMetadata metadata = rv.get();
            if (null != metadata) {
                return true;
            }
        } catch (KafkaException e) {
            LOGGER.error("数据发送错误:", e);
        } catch (InterruptedException | ExecutionException ie) {
            LOGGER.error("数据发送错误:", ie);
        }
        return false;
    }

    @Override
    public void flush() {
        try {
            kafkaProducer.flush();
        } catch (KafkaException e) {
            LOGGER.error("数据发送错误:", e);
        }
    }

    @Override
    public int partition(String key, String topic) {
        byte[] keyBytes = key.getBytes();
        List<PartitionInfo> partitions = kafkaProducer.partitionsFor(topic);
        int numPartitions = 0;
        for (PartitionInfo partitionInfo : partitions) {
            if (partitionInfo.leader() != null) {
                numPartitions += 1;
            }
        }
        return (Utils.murmur2(keyBytes) & 0x7fffffff) % numPartitions;
    }

}
