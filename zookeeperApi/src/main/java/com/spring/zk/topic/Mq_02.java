package com.spring.zk.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/13  11:05]
 * @DESC:
 */
public class Mq_02 {


    @Test
     public void producer() throws JMSException {
        String brokeUrl="failover:(tcp://hadoop01:61616,tcp://hadoop02:61616,tcp://hadoop03:61616)?Randomize=false";
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(null,null,brokeUrl);
        Connection conn=null;
        Session session=null;
        MessageProducer producer=null;
        try {
            // 创建连接
            conn=connectionFactory.createConnection();
            // 开启连接
            conn.start();
            session=conn.createSession(false,Session.CLIENT_ACKNOWLEDGE);

            Topic destination=session.createTopic("topic1");
            producer=session.createProducer(destination);
            TextMessage message=session.createTextMessage("我是超人");
            producer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {


            producer.close();
            session.close();
            conn.close();
        }
     }


    @Test
    public void consumer() throws JMSException {
        String brokeUrl="failover:(tcp://hadoop01:61616,tcp://hadoop02:61616,tcp://hadoop03:61616)?Randomize=false";
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(null,null,brokeUrl);
        Connection conn=null;
        Session session=null;
        MessageConsumer consumer=null;
        try {
            // 创建连接
            conn=connectionFactory.createConnection();
            // 开启连接
            conn.start();
            // 创建session   是否支持事物    签收的模式
            session=conn.createSession(false,Session.CLIENT_ACKNOWLEDGE);

            Topic destination=session.createTopic("topic1");
            consumer=session.createConsumer(destination);

            Message message=consumer.receive();
              System.out.println(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {


            consumer.close();
            session.close();
            conn.close();
        }
    }
}
