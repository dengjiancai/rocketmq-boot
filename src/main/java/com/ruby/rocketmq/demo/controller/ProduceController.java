package com.ruby.rocketmq.demo.controller;

import com.ruby.rocketmq.demo.model.JsonVo;
import com.ruby.rocketmq.demo.model.PayInfoReq;
import com.ruby.rocketmq.demo.service.ProducerService;
import com.ruby.rocketmq.demo.utils.ResMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producer")
@Api(tags = "ProduceController")
@Slf4j
public class ProduceController {
    /**使用RocketMq的生产者*/
    @Autowired
    private DefaultMQProducer defaultMQProducer;
     @Autowired
    private ProducerService producerService;

    @PostMapping(value = "/send/s" )
    @ApiOperation(value = "生产者同步发送消息")
    public ResMsg<SendResult> producerSend(@Validated @RequestBody JsonVo req) {
//        String msg = "demo msg test";
        log.info("开始同步发送消息：{}", req);
        SendResult sendResult  = producerService.producerSendS(req);
        log.info("消息发送响应信息：{}", sendResult.toString());

        return ResMsg.success(sendResult);
    }

    @PostMapping(value = "/send/async")
    @ApiOperation(value = "生产者异步发送消息")
    public ResMsg producerSendAsync(@Validated @RequestBody PayInfoReq req) {

        producerService.producerSendAsync(req);
        return ResMsg.success();
    }

}
