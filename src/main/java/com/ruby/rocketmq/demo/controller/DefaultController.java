package com.ruby.rocketmq.demo.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.consumer.PullResultExt;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: jiancaideng
 * Date: 2019/11/7
 * Time: 9:19
 * Description:
 */
@RestController
@RequestMapping("/api/default")
@Api(tags = "DefaultController")
@Slf4j
public class DefaultController {
    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.producer.groupName}")
    private String producerGroupName;
    @Value("${rocketmq.consumer.groupName}")
    private String consumerGroupName;
    @Value("${rocketmq.topic.payinfo}")
    private String topics;
    @Value("${rocketmq.tag.sync}")
    private String sync;

    private static final Map<MessageQueue, Long> offsetTable = new HashMap<MessageQueue, Long>();

//    @Resource
//    private DefaultMQProducer producer;

    @PostMapping(value = "/producer")
    public void producerQueueSelector() throws Exception{
        // 声明并初始化一个producer
        DefaultMQProducer producer = new DefaultMQProducer(producerGroupName);

        // 设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
        // NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);//3.2.6的版本没有该设置，在更新或者最新的版本中务必将其设置为false，否则会有问题
//        producer.setRetryTimesWhenSendFailed(3);

        // 调用start()方法启动一个producer实例
        producer.start();

        // 发送10条消息到Topic为TopicTest，tag为TagA，消息内容为“Hello RocketMQ”拼接上i的值
        for (int i = 0; i < 1; i++) {
            try {
                Message msg = new Message(this.topics, // topic
                        sync, // tag
                        "i" + i, ("Hello RocketMQ " + i).getBytes("utf-8")// body
                );

                // 调用producer的send()方法发送消息
                // 这里调用的是同步的方式，所以会有返回结果
                SendResult sendResult = producer.send(msg);

                //指定消息投递的队列，同步的方式，会有返回结果
				/*SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
					@Override
					public MessageQueue select(List<MessageQueue> queues, Message msg, Object queNum) {
						int queueNum = Integer.parseInt(queNum.toString());
						return queues.get(queueNum);
					}
				}, 0);*/

                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "," + i);
                // System.out.println(sendResult.getSendStatus()); //发送结果状态
                // 打印返回结果，可以看到消息发送的状态以及一些相关信息
                System.out.printf("%s%n", sendResult);
                System.out.println("当前消息投递到的队列是 : " + sendResult.getMessageQueue().getQueueId());
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        // 发送完消息之后，调用shutdown()方法关闭producer
        producer.shutdown();
    }

    @PostMapping(value = "/consumer")
    public void queueConsumer () throws Exception{
        offsetTable.clear();
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(this.consumerGroupName);
        consumer.setNamesrvAddr(this.namesrvAddr);
        consumer.start();
        try {
            Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues(this.topics);
            for (MessageQueue mq : mqs) {
                // System.out.println("Consume from the queue: " + mq);
                System.out.println("当前获取的消息的归属队列是: " + mq.getQueueId());
                // if (mq.getQueueId() == 0) {

                //System.out.println("我是从第1个队列获取消息的");
                // long offset = consumer.fetchConsumeOffset(mq, true);
                // PullResultExt pullResult
                // =(PullResultExt)consumer.pull(mq,
                // null, getMessageQueueOffset(mq), 32);
                // 消息未到达默认是阻塞10秒，private long consumerPullTimeoutMillis =
                // 1000 *
                // 10;
                PullResultExt pullResult = (PullResultExt) consumer.pullBlockIfNotFound(mq, null,
                        getMessageQueueOffset(mq), 32);
                putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                switch (pullResult.getPullStatus()) {

                    case FOUND:

                        List<MessageExt> messageExtList = pullResult.getMsgFoundList();
                        for (MessageExt m : messageExtList) {
                            System.out.println("收到了消息:" + new String(m.getBody()));
                        }
                        break;

                    case NO_MATCHED_MSG:
                        break;

                    case NO_NEW_MSG:
                        break;

                    case OFFSET_ILLEGAL:
                        break;

                    default:
                        break;
                }
            }
            // }
            consumer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offsetTable.put(mq, offset);
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offsetTable.get(mq);
        if (offset != null)
            return offset;
        return 0;
    }

}
