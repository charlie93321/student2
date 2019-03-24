package com.spring.zk.mq_spring;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/14  15:23]
 * @DESC:
 */
//@Component
public class MqListener {



    //当收到消息后，自动调用该方法,spring配置默认监听器，负责接收消息
    //@JmsListener(containerFactory="jmsListenerContainerFactory",destination = "topic-test-01")
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println(tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
