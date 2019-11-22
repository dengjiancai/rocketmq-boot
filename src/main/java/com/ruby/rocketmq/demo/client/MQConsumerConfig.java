package com.ruby.rocketmq.demo.client;

import com.ruby.rocketmq.demo.utils.RocketMQErrorEnum;
import com.ruby.rocketmq.demo.utils.RocketMQException;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConsumerConfig implements CommandLineRunner {
    public static final Logger logger = LoggerFactory.getLogger(MQConsumerConfig.class);

    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.consumer.groupName}")
    private String groupName;
//    @Value("${rocketmq.consumer.consumeThreadMin}")
//    private int consumeThreadMin;
//    @Value("${rocketmq.consumer.consumeThreadMax}")
//    private int consumeThreadMax;
    @Value("${rocketmq.topic.payinfo}")
    private String topicPayinfo;
    @Value("${rocketmq.consumer.instanceName}")
    private String instanceName;
    @Value("${rocketmq.consumer.consumeMessageBatchMaxSize}")
    private int consumeMessageBatchMaxSize;

    @Autowired
    private MQConsumeListenerConcurrently mqMessageListenerProcessor;

    public DefaultMQPushConsumer getRocketMQConsumer() throws RocketMQException{

        if (StringUtils.isEmpty(groupName)){
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL,"groupName is null !!!",false);
        }
        if (StringUtils.isEmpty(namesrvAddr)){
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL,"namesrvAddr is null !!!",false);
        }
        if(StringUtils.isEmpty(topicPayinfo)){
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL,"topics is null !!!",false);
        }

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
//        consumer.setConsumeThreadMin(consumeThreadMin);
//        consumer.setConsumeThreadMax(consumeThreadMax);

        /** 注册消息监听器:  两种 ，对应普通消息 和 顺序消息
         *  MQConsumeListenerConcurrently 和MessageListenerOrderly
         */
        consumer.registerMessageListener(mqMessageListenerProcessor);

        /**1. 客户端实例名称.客户端创建的多个 Producer、Consumer 实际是共用一个内部实例（这个实例包含网络连接、线程资源等）
         * 默认使用ip@pid作为instanceName(pid代表jvm名字)，配置后使用ip@instanceName作为consumer的唯一标示
         */
        consumer.setInstanceName(instanceName);

        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);


        /**2. 订阅模式：集群模式CLUSTERING（默认为集群） 和 广播模式BROADCASTING
         */
        consumer.setMessageModel(MessageModel.CLUSTERING);

        /**3. 本地队列： 默认值1000
         * 消费者不间断的从broker拉取消息，消息拉取到本地队列，然后本地消费线程消费本地消息队列，只是一个异步过程，拉取线程不会等待本地消费线程。
         * 这种模式实时性非常高，对消费者对本地队列有一个保护，因此本地消息队列不能无限大，否则可能会占用大量内存
         **/
//        consumer.setPullThresholdForQueue(1000);
        /** 轮询间隔: 默认为0
         * 消息拉取线程每隔多久拉取一次？
         */
//        consumer.setPullInterval(0);
        /**4. 消息消费数量：
         * 设置一次消费消息的条数，默认为1条
         * 监听器每次接受本地队列的消息是多少条
         */
//        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        /**5. 消费进度存储：默认为5秒
         * 每隔一段时间将各个队列的消费进度存储到对应的broker上
         */
//        consumer.setPersistConsumerOffsetInterval(5000);

        try {
            /** 一个topic在某broker上有3个队列,一个消费者消费这3个队列，那么该消费者和这个broker有几个连接？
             * 一个连接，消费单位与队列相关
             * 消费连接只跟broker相关，消费者将所有队列的消息拉取任务放到本地的队列，挨个拉取，拉取完毕后，又将拉取任务放到队尾，然后执行下一个拉取任务
             */
            /**
             * 设置该消费者订阅的主题和tag，如果是订阅该主题下的所有tag，
             * 则tag使用*；如果需要指定订阅该主题下的某些tag，则使用||分割，例如tag1||tag2||tag3
             */
        	/*String[] topicTagsArr = topics.split(";");
        	for (String topicTags : topicTagsArr) {
        		String[] topicTag = topicTags.split("~");
        		consumer.subscribe(topicTag[0],topicTag[1]);
			}*/
            consumer.subscribe(topicPayinfo
                    , "*");

            consumer.start();
            //logger.info("consumer is start !!! groupName:{},topics:{},namesrvAddr:{}",groupName,topics,namesrvAddr);

        } catch (Exception e) {
            // logger.error("consumer is start !!! groupName:{},topics:{},namesrvAddr:{}",groupName,topics,namesrvAddr,e);
            throw new RocketMQException(e);
        }

        return consumer;
    }

    /**
     * 启动后运行消费者
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        this.getRocketMQConsumer();
    }
}
