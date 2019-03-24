package com.spring.zk.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * 创建用户:狂飙的yellowcong<br/>
 * 创建日期:2017年12月9日<br/>
 * 创建时间:下午1:29:28<br/>
 * 机能概要:
 */
public class Provider { // activemq的服务器地址
     private static final String ACTIVEMQ_HOST = "failover:(tcp://hadoop01:61616,tcp://hadoop02:61616,tcp://hadoop03:61616)?Randomize=false";

     // 用户名
     private static final String USERNAME = null;
    // 密码
    private static final String PASSWORD = null;
    private static  Session SESSION_PRODUCE=null;
    private static  Session SESSION_CONSUMER=null;
    public static void main(String[] args) throws Exception {
        getSession(1);
        getSession(2);
        provider();

        //通过大小过滤
        customerByAge();
        SESSION_PRODUCE.close();
        SESSION_CONSUMER.close();

    }
    public static void getSession(int i) {
        try {
            // 1.获取工厂连接类
            ConnectionFactory fc = new ActiveMQConnectionFactory(USERNAME, PASSWORD, ACTIVEMQ_HOST);
            // 2.获取连接
            Connection conn = fc.createConnection(); conn.start();
            if(i==1)
            // 3.创建
                SESSION_PRODUCE = conn.createSession(true, Session.CLIENT_ACKNOWLEDGE);
            else
                SESSION_CONSUMER=conn.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

     public static void provider() throws JMSException {

         // 添加生产者
         MessageProducer producter = SESSION_PRODUCE.createProducer(null);
         MapMessage msg = SESSION_PRODUCE.createMapMessage();
         msg.setString("username", "张三");
         msg.setInt("age", 12);
         msg.setString("nickname", "zhangsan");
         // xxProperty 是用来检索的，过滤消息的
         msg.setIntProperty("age", 12);
         msg.setStringProperty("nickname", "zhangsan");
         MapMessage msg2 = SESSION_PRODUCE.createMapMessage();
         msg2.setString("username", "李四");
         msg2.setInt("age", 18);
         msg2.setString("nickname", "lisi");
         msg2.setIntProperty("age", 18);
         msg2.setStringProperty("nickname", "lisi");
         MapMessage msg3 = SESSION_PRODUCE.createMapMessage();
         msg3.setString("username", "王五");
         msg3.setInt("age", 122);
         msg3.setString("nickname", "wangwu");
         msg3.setIntProperty("age", 122);
         msg3.setStringProperty("nickname", "wangwu");
         MapMessage msg4 = SESSION_PRODUCE.createMapMessage();
         msg4.setString("username", "赵六");
         msg4.setInt("age", 24);
         msg4.setString("nickname", "zhaoliu");
         msg4.setIntProperty("age", 24);
         msg4.setStringProperty("nickname", "zhaoliu");
         MapMessage msg5 = SESSION_PRODUCE.createMapMessage();
         msg5.setString("username", "黄聪");
         msg5.setInt("age", 24);
         msg5.setString("nickname", "yellowcong");
         msg5.setIntProperty("age", 24);
         msg5.setStringProperty("nickname", "yellowcong");
         Destination destination = SESSION_PRODUCE.createQueue("test");
         // 发送消息
         producter.send(destination, msg, DeliveryMode.NON_PERSISTENT, 4, 1000 * 60);
         producter.send(destination, msg2, DeliveryMode.NON_PERSISTENT, 4, 1000 * 60);
         producter.send(destination, msg3, DeliveryMode.NON_PERSISTENT, 4, 1000 * 60);
         producter.send(destination, msg4, DeliveryMode.NON_PERSISTENT, 4, 1000 * 60);
         producter.send(destination, msg5, DeliveryMode.NON_PERSISTENT, 4, 1000 * 60);
         SESSION_PRODUCE.commit();
     }

     // @throws Exception
     public static void customerByAge() throws Exception {

         // 创建消费者
         Destination destination = SESSION_CONSUMER.createQueue("test");
         //查询年大于 18的数据 //字符串的过滤，需要有''
         MessageConsumer consumer = SESSION_CONSUMER.createConsumer(destination, "age >18 OR nickname = 'yellowcong'");
         // 设定消费者的监听器
         consumer.setMessageListener(new MessageListener() { public void onMessage(Message message) {
             // 当是Map 消息的情况
             if (message instanceof MapMessage) {
                 try { MapMessage msg = (MapMessage) message;
                 //接受消息
                 msg.acknowledge();
                 String username = msg.getString("username");
                 String nickname = msg.getString("nickname");
                 int age = msg.getInt("age");
                 System.out.printf("获取消费者信息\t%s:%s:%d\r\n", username, nickname, age);
                 } catch (JMSException e) {
                     e.printStackTrace();
                 }
             }
         }
         });
    }
}
