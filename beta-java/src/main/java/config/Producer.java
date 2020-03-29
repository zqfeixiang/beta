/**
 * Project Name:pomp-log-analysis
 * File Name:AnalysisProducer.java
 * Package Name:com.bocsoft.pomp.pomp_log_analysis.platform
 * Date:2017年4月20日下午4:22:42
 * Copyright (c) 2017, 开发七部 All Rights Reserved.
 *
*/

package config;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;


/**
 * ClassName:AnalysisProducer <br/>
 * Function: 将转换好的数据发送到kafka <br/>
 * Date:     2017年4月20日 下午4:22:42 <br/>
 * @author   st-xgq5611
 * @version  
 * @since    JDK 1.6
 * @see
 */
public interface Producer {
    /**
     * flush:(异步推数据). <br/>
     *
     * @author st-xgq5611
     * @since JDK 1.6
     */
    public void flush();
    
    /**
     * sendSync:阻塞发送数据
     *
     * @author st-xgq5611
     * @param key 主键
     * @param data 数据
     * @param topic topic
     * @return 结果
     * @since JDK 1.6
     */
    public Boolean sendSync(String topic, String key, String data);

    /**
     * send:发送等待返回值. <br/>
     *
     * @author st-xgq5611
     * @param topic topic
     * @param key key
     * @param data data
     * @return 结果
     * @since JDK 1.6
     */
    public Future<RecordMetadata> send(String topic, String key, String data);

    /**
     * partition:根据key和topic确定数据发送到的分区
     *
     * @author st-xgq5611
     * @param key producerKey
     * @param topic topic
     * @return 分区
     * @since JDK 1.6
     */
    public int partition(String key, String topic);
}

