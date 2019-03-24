package com.spring.zk.ptp;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/13  11:05]
 * @DESC:
 */
public class Mq_01 {


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
            // 创建session   是否支持事物    签收的模式
            /**
             * 第一个参数是控制事务，true表示开始事务，
             * 此时必须session.commit()才能将消息提交到服务器队列中；
             * false表示不启用事务，必须session.close()才能提交消息到服务器队列，
             * 不调用session.close(),服务器将收不到消息，一般用fasle
             *
             *
             * 第二个参数是控制消息的，可以是自动提交为Session.AUTO_ACKNOWLEDGE，
             * 当客户端从 receive 或 onMessage成功返回时，Session 自动签收客户端的这条消息的收条；
             * 可以是Session.CLIENT_ACKNOWLEDGE客户端必须通过调用消息的
             * message.acknowledge()方法才能签收消息。
             * 对于生产者来说，区别不大，我没看出两者有什么区别；
             * 对于消费者来说如果Session设置为AUTO_ACKNOWLEDGE ，
             * 接收到消息（receive 或 onMessage成功返回时），即为消费成功，
             * 然后从队列里移除该数据。不关心该数据有没有正确被处理成我们想要的结果；
             * Session设置为CLIENT_ACKNOWLEDGE 时，必须手动调用acknowledge 方法才为消费成功，
             * 然后从队列里移除该条数据。生产者P和消费者C之间的设置互不影响
             *
             */
            session=conn.createSession(false,Session.CLIENT_ACKNOWLEDGE);

            Queue destination=session.createQueue("producer");
            producer=session.createProducer(destination);
            TextMessage message=session.createTextMessage("我是大王八");
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

            Queue destination=session.createQueue("producer");
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
