package com.ruby.rocketmq.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/producer")
@Api(tags = "ProduceController")
@Slf4j
public class ProduceController {
    /**使用RocketMq的生产者*/
    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Value("${rocketmq.topic}")
    private String topic;

    @PostMapping(value = "/send/s" )
    @ApiOperation(value = "生产者同步发送消息")
    public void producerSend(@RequestParam String msg) throws Exception{
//        String msg = "demo msg test";
        log.info("开始同步发送消息：{}", msg);
        Message sendMsg = new Message(topic,"*",msg.getBytes());
        //默认3秒超时
        SendResult sendResult = defaultMQProducer.send(sendMsg);
        log.info("消息发送响应信息：{}", sendResult.toString());
    }

    @PostMapping(value = "/send/async")
    @ApiOperation(value = "生产者异步发送消息")
    public void producerSendAsync() throws Exception{
        try {
            String msg = "demo msg test";
            log.info("开始异步发送消息：{}", msg);
            Message sendMsg = new Message(topic,"*",msg.getBytes());
            //默认3秒超时
            defaultMQProducer.send(sendMsg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n",  sendResult.getMsgId());
                    log.info("异步消息发送响应信息：{}", sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    log.error("异步消息发送失败信息：{}", e.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
