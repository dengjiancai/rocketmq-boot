package com.ruby.rocketmq.demo.config;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: jiancaideng
 * Date: 2019/11/5
 * Time: 15:13
 * Description:
 */
@Configuration
public class RocketmqConfig {
    @Value("${rocketmq.consumer.groupName}")
    private String consumerGroupName;
    @Value("${rocketmq.producer.groupName}")
    private String producerGroupName;
    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.consumer.instanceName}")
    private String instanceName;
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private int sendMsgTimeout;
    @Value("${rocketmq.producer.maxMessageSize}")
    private int maxMessageSize;
    @Value("${rocketmq.producer.compressOver}")
    private int compressOver;
    @Value("${rocketmq.topic}")
    private String topic;
    @Value("${rocketmq.tag}")
    private String tag;

//    @Bean
//    public DefaultMQProducer getRocketMQProducer() {
//        DefaultMQProducer producer;
//        producer = new DefaultMQProducer(this.producerGroupName);
//        producer.setNamesrvAddr(this.namesrvAddr);
////        producer.setInstanceName(instanceName);
////        producer.setSendMsgTimeout(this.sendMsgTimeout);
////        producer.setCompressMsgBodyOverHowmuch(this.compressOver);
////        producer.setMaxMessageSize(this.maxMessageSize);
//
//        try {
//            producer.start();
//        } catch (MQClientException e) {
//            System.out.println(e);
//        }
//        return producer;
//    }

//    @Bean
//    public DefaultMQPushConsumer getRocketMQConsumer() {
//
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(this.consumerGroupName);
//        consumer.setNamesrvAddr(this.namesrvAddr);
//        consumer.setInstanceName(this.instanceName);
//        consumer.setConsumeMessageBatchMaxSize(1);
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
//        consumer.setConsumeConcurrentlyMaxSpan(2000);  //单队列并行消费最大跨度，用于流控
//        consumer.setPullThresholdForQueue(1000);       // 一个queue最大消费的消息个数，用于流控
//        consumer.setPullInterval(1000);                //消息拉取时间间隔，默认为0，即拉完一次立马拉第二次，单位毫秒
//        consumer.setMessageModel(MessageModel.CLUSTERING);  //消费模式，集群消费
//
//
//        try {
//            consumer.subscribe(this.topic, tag);
//            consumer.registerMessageListener((MessageListenerConcurrently) (msgList, consumeConcurrentlyContext) -> {
//                MessageExt msg = null;
//                for (MessageExt aMsgList : msgList) {
//                    msg = aMsgList;
//                    System.out.println("收到MQ消息："+msg);
//                }
//
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            });
//            consumer.start();
//            System.out.println("已启动Conusmer【gruop:" + this.consumerGroupName + "，instance:" + this.instanceName
//                    + "】，监听TOPIC-{" + this.topic + "},tag-{" + this.tag + "}");
//        } catch (MQClientException e) {
//            System.out.println(e);
//        }
//        return consumer;
//    }
}
