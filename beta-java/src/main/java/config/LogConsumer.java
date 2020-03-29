/**
 * Project Name:pomp-platform-logstash
 * File Name:LogConsumer.java
 * Package Name:com.bocsoft.bocebiz.pomp.platform.service
 * Date:2017年4月25日上午9:44:14
 * Copyright (c) 2017, 开发七部 All Rights Reserved.
 */

package config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
 * ClassName: LogConsumer <br/>
 * date: 2017年9月26日 下午2:15:45 <br/>
 *
 * @author st-lxf
 * @version
 * @since JDK 1.6
 */
public class LogConsumer implements Consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogConsumer.class);
    private KafkaConsumer<String, String> kafkaConsumer;
    private static final String[] XML_CONFIG_FILES = {"biz-service.xml"};
    static ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext(XML_CONFIG_FILES);

    public LogConsumer(Properties consumerConfig) {
        this.kafkaConsumer = new KafkaConsumer<String, String>(consumerConfig);
    }

    public static void main(String[] args) {
        PropertiesBean propertiesBean = (PropertiesBean) springContext.getBean("propertiesBean");
        Properties consumerConfig = propertiesBean.getConsumerConfig();
        Consumer consumer = new LogConsumer(consumerConfig);
        consumer.setTopic("TestTopic");
        while (true) {
            JSONObject object = consumer.poll(2000);
            LOGGER.info(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "->" + object);
        }
    }

    @Override
    public void setTopic(String topic) {
        //订阅实现负载监听，在分配分区发生变化的时候做相对应的操作，实现容灾。
        kafkaConsumer.subscribe(Arrays.asList(topic.split(",")));
    }

    /**
     *
     * getConsumerAssignment:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author st-lxf
     * @return Set<TopicPartition>
     * @since JDK 1.6
     */
    public Set<TopicPartition> getConsumerAssignment() {
        Set<TopicPartition> partitions = kafkaConsumer.assignment();
        return partitions;
    }

    /**
     *
     * pausePartitions:(这里用一句话描述这个方法的作用). <br/>
     * @author st-lxf
     * @param partition 分区
     * @since JDK 1.6
     */
    public void pausePartitions(TopicPartition partition) {
        List<TopicPartition> list = Arrays.asList(partition);
        kafkaConsumer.pause(list);

    }

    /**
     *
     * resumePartitions:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author st-lxf
     * @param partition 分区
     * @since JDK 1.6
     */
    public void resumePartitions(TopicPartition partition) {
        LOGGER.info("resumePartitions:" + partition);
        List<TopicPartition> list = Arrays.asList(partition);
        kafkaConsumer.resume(list);
    }

    /**
     *
     * getCurrentOffset:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author st-lxf
     * @param partition 分区
     * @return Long
     * @since JDK 1.6
     */
    public Long getCurrentOffset(TopicPartition partition) {
        return kafkaConsumer.position(partition);
    }

    @Override
    public JSONObject poll(long timeout) {
        JSONObject jsonConsumerRecords = new JSONObject();
        try {
            ConsumerRecords<String, String> result = kafkaConsumer.poll(timeout);
            for (TopicPartition tp : result.partitions()) {
                LOGGER.debug("获取数据分区：" + tp.partition());
                JSONArray partitionData = new JSONArray();
                Iterator<ConsumerRecord<String, String>> iterator = result.records(tp).iterator();
                LOGGER.debug("获取数据数量：" + result.records(tp).size());
                while (iterator.hasNext()) {
                    ConsumerRecord<String, String> consumerRecord = iterator.next();
                    JSONObject record = new JSONObject();
                    record.put("key", consumerRecord.key());
                    record.put("offset", consumerRecord.offset());
                    record.put("topic", consumerRecord.topic());
                    record.put("partition", consumerRecord.partition());
                    record.put("value", consumerRecord.value());
                    partitionData.add(record);
                }
                if (jsonConsumerRecords.containsKey(tp.topic())) {
                    jsonConsumerRecords.getJSONObject(tp.topic()).put(String.valueOf(tp.partition()), partitionData);
                } else {
                    JSONObject topicData = new JSONObject();
                    topicData.put(String.valueOf(tp.partition()), partitionData);
                    jsonConsumerRecords.put(tp.topic(), topicData);
                }
            }
        } catch (KafkaException e) {
            LOGGER.error("读取数据错误", e);
        }

        return jsonConsumerRecords;
    }

    @Override
    public void setPartitionOffset(TopicPartition partition, long offset) {
        kafkaConsumer.seek(partition, offset);
        LOGGER.warn("重置分区[{}]offset{}", partition, offset);
    }

    @Override
    public Long getPartitionBeginningOffset(TopicPartition partition) {
        List<TopicPartition> list = Arrays.asList(partition);
        long tmp = kafkaConsumer.position(partition);
        kafkaConsumer.seekToBeginning(list);
        long offset = kafkaConsumer.position(partition);
        kafkaConsumer.seek(partition, tmp);
        return offset;
    }

    @Override
    public void commit(String topic, String partition, long offset) {
        Map<TopicPartition, OffsetAndMetadata> map = new HashMap<TopicPartition, OffsetAndMetadata>();
        map.put(new TopicPartition(topic, Integer.parseInt(partition)), new OffsetAndMetadata(offset));
        kafkaConsumer.commitSync(map);
    }

    @Override
    public void commit() {
        kafkaConsumer.commitSync();
    }


}
