package com.spring.zk.mq_spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/13  22:58]
 * @DESC:
 */
public class MainApp {

    public static void main(String[] args){
        AnnotationConfigApplicationContext app=  new AnnotationConfigApplicationContext(MqSpringConfiguration.class);


        JmsTemplate template= (JmsTemplate) app.getBean("jmsTemplate");

        template.send(new MessageCreator(){

            @Override
            public Message createMessage(Session session) throws JMSException {
                 TextMessage message=session.createTextMessage("我是大傻逼");

                 return message;
            }
        });



        app.close();
    }
}
