package com.ruby.rocketmq.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/producer")
@Api(tags = "ProduceController")
@Slf4j
public class ConsumerController {
    /**使用RocketMq的生产者*/
    @Autowired
    private DefaultMQProducer defaultMQProducer;
    @Value("${rocketmq.topic}")
    private String topic;

    @PostMapping(value = "/push/consumer")
    @ApiOperation(value = "push消费消息")
    public void consumer() throws Exception{
        String msg = "demo msg test";
        log.info("开始发送消息："+msg);
        Message sendMsg = new Message(topic,"*",msg.getBytes());
        //默认3秒超时
        SendResult sendResult = defaultMQProducer.send(sendMsg);
        log.info("消息发送响应信息："+sendResult.toString());
    }
}
