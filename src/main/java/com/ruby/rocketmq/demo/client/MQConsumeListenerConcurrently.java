package com.ruby.rocketmq.demo.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruby.rocketmq.demo.model.JsonVo;
import com.ruby.rocketmq.demo.model.PayInfoReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Slf4j
public class MQConsumeListenerConcurrently implements MessageListenerConcurrently{
    private static final Logger logger = LoggerFactory.getLogger(MQConsumeListenerConcurrently.class);

    @Value("${rocketmq.topic.payinfo}")
    private String topicPayinfo;
    @Value("${rocketmq.tag.async}")
    private String tagAsync;
    @Value("${rocketmq.tag.sync}")
    private String tagSync;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if(CollectionUtils.isEmpty(msgs)){
            logger.info("接收到的消息为空，不做任何处理");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = msgs.get(0);
        String msg = new String(messageExt.getBody());
        //logger.info("接收到的消息是："+messageExt.toString());
        if(messageExt.getTopic().equals(topicPayinfo)){
            if(messageExt.getTags().equals(tagAsync)){
                log.info("消费者接收到的[messageExt]异步消息：{}", messageExt);
                JsonVo jsonVo = JSONObject.parseObject(messageExt.getBody(), JsonVo.class);
                log.info("消费者接收到的[{}]消息：{}", tagAsync, jsonVo);

                int reconsumeTimes = messageExt.getReconsumeTimes();
//                if(reconsumeTimes == 3){
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                }
                //TODO 处理对应的业务逻辑
            }
            if(messageExt.getTags().equals(tagSync)){
                log.info("消费者接收到的[messageExt]同步消息：{}", messageExt);
                PayInfoReq payInfoReq = JSON.parseObject(messageExt.getBody(), PayInfoReq.class);
                logger.info("消费者接收到的[{}]消息：{}",tagSync, payInfoReq);

                int reconsumeTimes = messageExt.getReconsumeTimes();
//                if(reconsumeTimes == 3){
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                }
                //TODO 处理对应的业务逻辑
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
