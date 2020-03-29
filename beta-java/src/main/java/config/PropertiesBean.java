/**
 * Project Name:pomp-log-analysis_develop
 * File Name:PropertiesBean.java
 * Package Name:com.bocsoft.pomp.pomp_log_analysis.config
 * Date:2017年12月23日上午10:21:09
 * Copyright (c) 2017, 开发七部 All Rights Reserved.
 *
*/

package config;

import java.util.Properties;

/**
 * ClassName:PropertiesBean <br/>
 * Function: 配置文件中参数对应的对象. <br/>
 * Date: 2017年12月23日 上午10:21:09 <br/>
 * 
 * @author by4487
 * @version
 * @since JDK 1.6
 * @see
 */
public class PropertiesBean {
    private Properties consumerConfig;
    private Properties producerConfig;

    private Integer heartbeatInterval;
    private Integer timeOut;
    private Integer restartRefreshInterval;

    private Properties taskExecutorConfig;
    private Properties exceptionConfig;

    private String invalidTopic;
    private String infoTopic;
    
    private String statisticsInfoInterval;
    private String statisticsInfoCount;

    private Properties standbyConfig;
    private Properties hdfsConfig;
    private Properties esConfig;

    public Properties getConsumerConfig() {
        return consumerConfig;
    }

    public void setConsumerConfig(Properties consumerConfig) {
        this.consumerConfig = consumerConfig;
    }

    public Properties getProducerConfig() {
        return producerConfig;
    }

    public void setProducerConfig(Properties producerConfig) {
        this.producerConfig = producerConfig;
    }

    public Integer getHeartbeatInterval() {
        return heartbeatInterval;
    }

    public void setHeartbeatInterval(Integer heartbeatInterval) {
        this.heartbeatInterval = heartbeatInterval;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public Integer getRestartRefreshInterval() {
        return restartRefreshInterval;
    }

    public void setRestartRefreshInterval(Integer restartRefreshInterval) {
        this.restartRefreshInterval = restartRefreshInterval;
    }

    public Properties getTaskExecutorConfig() {
        return taskExecutorConfig;
    }

    public void setTaskExecutorConfig(Properties taskExecutorConfig) {
        this.taskExecutorConfig = taskExecutorConfig;
    }

    public Properties getExceptionConfig() {
        return exceptionConfig;
    }

    public void setExceptionConfig(Properties exceptionConfig) {
        this.exceptionConfig = exceptionConfig;
    }

    public String getInvalidTopic() {
        return invalidTopic;
    }

    public void setInvalidTopic(String invalidTopic) {
        this.invalidTopic = invalidTopic;
    }

    public String getInfoTopic() {
        return infoTopic;
    }

    public void setInfoTopic(String infoTopic) {
        this.infoTopic = infoTopic;
    }

    public Properties getStandbyConfig() {
        return standbyConfig;
    }

    public void setStandbyConfig(Properties standbyConfig) {
        this.standbyConfig = standbyConfig;
    }

    public Properties getHdfsConfig() {
        return hdfsConfig;
    }

    public void setHdfsConfig(Properties hdfsConfig) {
        this.hdfsConfig = hdfsConfig;
    }
    
    public Properties getEsConfig() {
        return esConfig;
    }

    public void setEsConfig(Properties esConfig) {
        this.esConfig = esConfig;
    }

    public String getStatisticsInfoInterval() {
        return statisticsInfoInterval;
    }

    public void setStatisticsInfoInterval(String statisticsInfoInterval) {
        this.statisticsInfoInterval = statisticsInfoInterval;
    }

    public String getStatisticsInfoCount() {
        return statisticsInfoCount;
    }

    public void setStatisticsInfoCount(String statisticsInfoCount) {
        this.statisticsInfoCount = statisticsInfoCount;
    }

}
