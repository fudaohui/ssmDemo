/*
 * 文件名： KafkaClient.java
 *
 * 创建日期： 2016年6月17日
 *
 * Copyright(C) 2016, by <a href="mailto:liws@xingyuanauto.com">liws</a>.
 *
 * 原始作者: liws
 *
 */
package com.fdh.demo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer {

    private org.apache.kafka.clients.producer.Producer<String, String> serverProducer;

    private Properties props;

    /**
     * 初始化kafka提供者
     */
    private void init() {
        serverProducer = new KafkaProducer<String, String>(props);
    }

    /**
     * 发送掉线ICU的UID给转发网关处理
     */
    public void send(String topic,String key, String reqStreamData) throws Exception {
//        ProducerRecord<String,String> record = new ProducerRecord<String,String>(topic,reqStreamData);
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, reqStreamData);
        serverProducer.send(record);

    }

    /**
     * @return the props
     */

    public Properties getProps() {
        return props;
    }

    /**
     * @param props the props to set
     */
    public void setProps(Properties props) {
        this.props = props;
    }
}
