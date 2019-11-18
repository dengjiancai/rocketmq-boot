package com.ruby.rocketmq.demo.task;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: jiancaideng
 * Date: 2019/11/5
 * Time: 15:48
 * Description:
 */
@Component
public class RocketmqTask {
//    @Resource
//    private DefaultMQProducer producer;

    /**
     * 每5秒执行一次
     */
//    @Scheduled(cron = "0/5 * *  * * ?")
//    private void sendMsgToMq() {
//        String str = "发送测试消息";
//        Message msg;
//        try {
//            msg = new Message("test-demo"
//                    , "111"
//                    , UUID.randomUUID().toString()
//                    , str.getBytes("utf-8"));
//            SendResult result = producer.send(msg);
//            if (result.getSendStatus() == SendStatus.SEND_OK) {
//                System.out.println("消息发送成功");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
