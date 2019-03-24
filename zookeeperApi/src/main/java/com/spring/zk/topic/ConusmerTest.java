package com.spring.zk.topic;

import org.junit.Test;

import javax.jms.*;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/13  17:51]
 * @DESC:  消费确认
 */
public class ConusmerTest {

      static  MqConfiguration configuration=new MqConfiguration();
      static  String topicName="topic-07";

      static  void  init(){
          configuration.setAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
          configuration.setPassword(null);
          configuration.setUrl("failover:(tcp://hadoop01:61616,tcp://hadoop02:61616,tcp://hadoop03:61616)?Randomize=false");
          configuration.setUserName(null);
          configuration.setTransacted(false);
          configuration.setDestination(topicName);


      }
      @Test
      public void  produce() throws JMSException {
          init();
          MqProducer producer=new MqProducer(configuration);

          MapMessage message1=producer.getSession().createMapMessage();
          message1.setInt("id",1);
          message1.setString("name","tom");
          message1.setInt("age",12);

         MapMessage message2=producer.getSession().createMapMessage();
         message2.setInt("id",2);
         message2.setString("name","小凡");
         message2.setInt("age",25);

         MapMessage message3=producer.getSession().createMapMessage();
         message3.setInt("id",3);
         message3.setString("name","田不易");
         message3.setInt("age",88);

          producer.sendMessage(null,message1,message2,message3);


      }

      static  String clientId="c-02";
      static  String sqlCondition=null;//" age >= 25 ";


      public static void main(String[] args){
          init();

          for (int i = 0; i <2 ; i++) {

                new Thread(new ConsumerRole("客户端"+i)).start();
          }

      }


      static class ConsumerRole implements Runnable{
          private String name;

          public ConsumerRole(String name) {
              this.name = name;
          }

          @Override
          public void run() {
              System.out.println("开始...............");
              MqConsumer consumer = null;
              try {
                  consumer = new MqConsumer(configuration, clientId + "-09", sqlCondition);
              } catch (JMSException e) {
                  e.printStackTrace();
              }

              consumer.receive(message -> {
                  if(message instanceof  MapMessage){
                      try {
                            System.out.println(name + "接收到MAP数据:" +getMap((MapMessage) message));
                      } catch (JMSException e) {
                          e.printStackTrace();
                      }
                  }else
                  System.out.println(name + "接收到数据:" + message);
              });

          }

          private Map<String, Object> getMap(MapMessage message) throws JMSException {
              Map<String,Object> map=new LinkedHashMap<>();
             Enumeration<String> names= message.getMapNames();
             while(names.hasMoreElements()){
                   String key=  names.nextElement();
                   map.put(key,message.getObject(key));
             }

             return map;

          }
      }




}
