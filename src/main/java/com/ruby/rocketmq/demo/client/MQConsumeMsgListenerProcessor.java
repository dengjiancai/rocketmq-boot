package com.ruby.rocketmq.demo.client;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class MQConsumeMsgListenerProcessor implements MessageListenerConcurrently {
    private static final Logger logger = LoggerFactory.getLogger(MQConsumeMsgListenerProcessor.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if(CollectionUtils.isEmpty(msgs)){
            logger.info("接收到的消息为空，不做任何处理");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = msgs.get(0);
        String msg = new String(messageExt.getBody());
        //logger.info("接收到的消息是："+messageExt.toString());
        logger.info("接收到的消息是："+msg);
        if(messageExt.getTopic().equals("你的topic")){
            if(messageExt.getTags().equals("你的tag")){
                int reconsumeTimes = messageExt.getReconsumeTimes();
                if(reconsumeTimes == 3){
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                //TODO 处理对应的业务逻辑
            }
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
