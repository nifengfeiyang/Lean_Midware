package com.szm.rocket.performance.control;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 支付信息控制器
 *
 * @author 沈子明
 * @date 2020/2/18 10:40
 **/
@RestController
@RequestMapping("/rocket")
public class RocketController {

    /**
     * 下标自增原子类实现
     */
    static AtomicInteger INDEX = new AtomicInteger(1);
    /**
     * 下标前缀，取值为：{@value}
     */
    private final static String PREFIX = "index_";

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.producer.send-message-timeout}")
    private Integer messageTimeOut;

    /**
     * 根据请求参数，处理相关业务后返回前端json媒体
     *
     * @return 返回ResponseVO实体
     * @throws IOException 当插入失败时，可能会产生IOException异常
     */
    @RequestMapping("/index")
    public String index(String msgBody) throws IOException {
        long start = System.currentTimeMillis();
        sendMsg(msgBody);
        long end = System.currentTimeMillis();
        String result = "used time is : " + (end - start);
        System.out.println(result);
        return result;
    }




    /**
     * 发送普通消息
     */
    public void sendMsg(String msgBody){
        rocketMQTemplate.syncSend("queue_test_topic",MessageBuilder.withPayload(msgBody).build());
    }

    /**
     * 发送异步消息 在SendCallback中可处理相关成功失败时的逻辑
     */
    public void sendAsyncMsg(String msgBody){
        rocketMQTemplate.asyncSend("queue_test_topic", MessageBuilder.withPayload(msgBody).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                // 处理消息发送成功逻辑
            }
            @Override
            public void onException(Throwable e) {
                // 处理消息发送异常逻辑
            }
        });
    }

    /**
     * 发送延时消息<br/>
     * 在start版本中 延时消息一共分为18个等级分别为：1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h<br/>
     */
    public void sendDelayMsg(String msgBody, Integer delayLevel){
        rocketMQTemplate.syncSend("queue_test_topic",MessageBuilder.withPayload(msgBody).build(),messageTimeOut,delayLevel);
    }

    /**
     * 发送带tag的消息,直接在topic后面加上":tag"
     */
    public void sendTagMsg(String msgBody){
        rocketMQTemplate.syncSend("queue_test_topic:tag1",MessageBuilder.withPayload(msgBody).build());
    }

}
