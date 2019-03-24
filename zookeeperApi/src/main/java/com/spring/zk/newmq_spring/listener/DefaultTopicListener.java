package com.spring.zk.newmq_spring.listener;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/14  17:40]
 * @DESC:
 */
@Component("topicListener")
public class DefaultTopicListener implements MessageListener {


    @Override
    public void onMessage(Message message) {
        try {
            message.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        System.out.println("topic 接收到消息:"+message);
    }
}
