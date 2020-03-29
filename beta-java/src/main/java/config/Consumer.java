/**
 * Project Name:pomp-log-analysis
 * File Name:AnalysisConsumer.java
 * Package Name:com.bocsoft.pomp.pomp_log_analysis.platform
 * Date:2017年4月19日下午3:45:45
 * Copyright (c) 2017, 开发七部 All Rights Reserved.
 *
*/

package config;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

import java.util.Set;

/**
 * ClassName:AnalysisConsumer <br/>
 * 
 * Date:     2017年4月19日 下午3:45:45 <br/>
 * @author   st-xgq5611
 * @version  
 * @since    JDK 1.6
 * @see
 */
public interface Consumer {
    /**
     * setTopic:(设置consumer订阅). <br/>
     *
     * @author st-xgq5611
     * @param topic topic
     * @since JDK 1.6
     */
    public void setTopic(String topic);
    /**
     * poll:(从kafka中获取数据). <br/>
     *
     * @author st-xgq5611
     * @param timeout 超时时间
     * @return 结果
     * @since JDK 1.6
     */
    public JSONObject poll(long timeout);
    /**
     * getConsumerAssignment:(获取订阅partitions). <br/>
     *
     * @author st-xgq5611
     * @return partitions
     * @since JDK 1.6
     */
    public Set<TopicPartition> getConsumerAssignment();
    /**
     * pausePartitions:(暂停从指定partition取值). <br/>
     *
     * @author st-xsf6626
     * @param partition 分区
     * @since JDK 1.6
     */
    public void pausePartitions(TopicPartition partition);
    /**
     * resumePartitions:(回复指定partition取值). <br/>
     *
     * @author st-xsf6626
     * @param partition 分区
     * @since JDK 1.6
     */
    public void resumePartitions(TopicPartition partition);
    /**
     * getCurrentOffset:(获取读取前的offset). <br/>
     *
     * @author st-xgq5611
     * @param partition partition
     * @return offset
     * @since JDK 1.6
     */
    public Long getCurrentOffset(TopicPartition partition);

    /**
     * setPartitionOffset:(设置offset). <br/>
     *
     * @author st-xgq5611
     * @param partition partition
     * @param offset offset
     * @since JDK 1.6
     */
    public void setPartitionOffset(TopicPartition partition, long offset);

    /**
     * getPartitionBeginningOffset:(). <br/>
     *
     * @author st-xgq5611
     * @param partition 分区
     * @return beginning offset
     * @since JDK 1.6
     */
    public Long getPartitionBeginningOffset(TopicPartition partition);
    /**
     * Commit:(提交分区offset). <br/>
     *
     * @author zx4492
     * @param topic topic
     * @param partition partition
     * @param offset offset
     * @since JDK 1.6
     */
    public void commit(String topic, String partition, long offset);
    
    /**
     * commit:(提交分区). <br/>
     *
     * @author st-xgq5611
     * @since JDK 1.6
     */
    public void commit();

}

