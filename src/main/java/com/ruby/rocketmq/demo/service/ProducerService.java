package com.ruby.rocketmq.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruby.rocketmq.demo.model.JsonVo;
import com.ruby.rocketmq.demo.model.PayInfoReq;
import com.ruby.rocketmq.demo.model.PayInfoRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerService {

    /**使用RocketMq的生产者*/
    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Value("${rocketmq.topic.payinfo}")
    private String topicPayinfo;
    @Value("${rocketmq.tag.async}")
    private String tagAsync;
    @Value("${rocketmq.tag.sync}")
    private String tagSync;
    /**
     * 同步发送
     * @param req
     * @return
     */
    public SendResult producerSendS(JsonVo req){
        SendResult sendResult = null;
        try {
            String json = JSONObject.toJSONString(req);
//            JsonVo jsonVo = JSONObject.parseObject(json, JsonVo.class);//入参是对象的Json string
            log.info("生产者同步发送消息[Json字符串]：{}", json);
            byte[] bytes = JSONObject.toJSONBytes(req);// 入参是对象JsonVo 不能是String 否则解析失败
//            JsonVo jsonVo2 = JSONObject.parseObject(bytes, JsonVo.class);
//            log.info("生产者同步发送消息[对象反序列化]：{}", jsonVo2);
            Message sendMsg = new Message(topicPayinfo, tagSync, bytes);
            //默认3秒超时
            sendResult = defaultMQProducer.send(sendMsg);
        }catch (Exception e){
            log.error("消息发送异常：{}", e.toString());
        }
        return sendResult;
    }

    /**
     * 异步发送
     * @throws Exception
     */
    public void producerSendAsync(PayInfoReq req){
        try {
//            String msg = "demo msg test";
            String reqJson = JSON.toJSONString(req);
            log.info("开始异步发送消息：{}", reqJson);
            byte[] bytes = JSON.toJSONBytes(reqJson);
            Message sendMsg = new Message(topicPayinfo, tagAsync, bytes);
            //默认3秒超时
            defaultMQProducer.send(sendMsg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d---------------- OK-----------",  sendResult.getMsgId());
                    log.info("异步消息发送响应信息：{}", sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    log.error("异步消息发送失败信息：{}", e.getMessage());
                }
            });
        } catch (Exception e) {
            log.error("消息发送异常：{}", e.toString());
        }
    }
}
