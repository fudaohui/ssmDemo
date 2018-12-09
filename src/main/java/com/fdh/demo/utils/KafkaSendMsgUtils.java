package com.fdh.demo.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

public class KafkaSendMsgUtils {


    public static final ClassPathXmlApplicationContext CONTEXT = new ClassPathXmlApplicationContext("classpath:spring/kafka-producer.xml");

    public static <K, T> void sendMessage(String topic, K key, T data) {

        KafkaTemplate<K, T> kafkaTemplate = (KafkaTemplate<K, T>) CONTEXT.getBean("kafkaTemplate");
        ListenableFuture<SendResult<K, T>> listenableFuture = kafkaTemplate.send(topic, key, data);
        //发送成功回调
        SuccessCallback<SendResult<K, T>> successCallback = new SuccessCallback<SendResult<K, T>>() {
            @Override
            public void onSuccess(SendResult<K, T> result) {
                //成功业务逻辑
                System.out.println("成功");
            }
        };
        //发送失败回调
        FailureCallback failureCallback = new FailureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                //失败业务逻辑
                throw new RuntimeException(ex);
            }
        };
        listenableFuture.addCallback(successCallback, failureCallback);
    }
//    public static void main(String[] args) {
//        for (int i = 0; i < 5; i++) {
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            //KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate<String, String>) CONTEXT.getBean("kafkaTemplate");
//            KafkaSendMsgUtils.sendMessage("topic1",0,null,"key","kafka-test");
//        }
//    }
}
