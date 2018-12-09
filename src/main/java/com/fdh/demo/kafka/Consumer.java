package com.fdh.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Consumer {
    private Properties props;
    private List<String> topicsList;
    /**
     * topic字符串，从config配置文件中读取
     */
//    private String topicStr;

    private KafkaConsumer consumer;

    /**
     * 根据配置 初始化消费者
     */
    public void init() {
        topicsList = new ArrayList<>();
        topicsList.add("test1");
        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(topicsList);
        this.run();
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public List<String> getTopicsList() {
        return topicsList;
    }

    public void setTopicsList(List<String> topicsList) {
        this.topicsList = topicsList;
    }

//    public String getTopicStr() {
//        return topicStr;
//    }
//
//    public void setTopicStr(String topicStr) {
//        this.topicStr = topicStr;
//    }

    public void run() {

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records){
                System.out.printf("----offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }
}
