package com.spring.zk.newmq_spring;

import com.spring.zk.wechat.WechatUtil;
import org.apache.activemq.store.jdbc.JDBCPersistenceAdapter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.EmbeddedValueResolver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Scanner;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/14  17:18]
 * @DESC:
 */
public class NewMainApp {


      public static void main(String[] args){

          AnnotationConfigApplicationContext app= new AnnotationConfigApplicationContext(NewqMqConfiguration.class);




          JmsTemplate queue= (JmsTemplate) app.getBean("queueTemplate");

          //JmsTemplate topic= (JmsTemplate) app.getBean("topicTemplate");


          Scanner scanner=new Scanner(System.in);
          String line="";

          while(line!=null){
              System.out.print("请输入你要发送的消息send表示发送>");
              line=scanner.nextLine();

              if(StringUtils.isEmpty(line)){
                  continue;
              }

              Object finalLine = line;
           /*   queue.send(session->{
                      Message message=null;
                      try {
                          message=session.createTextMessage(finalLine);
                      }catch (Exception e){

                      }
                      return message;
                  });*/

           /*   queue.convertAndSend(finalLine, new MessagePostProcessor() {
                  @Override
                  public Message postProcessMessage(Message message) throws JMSException {
                      WechatUtil.sendMessage("ZengXiaoYu", "消息发送成功,现在的时间是:"+System.currentTimeMillis());
                      return message;
              }
              });*/


           Message back=queue.sendAndReceive(session->{
               Message message=null;
               try {
                   message=session.createTextMessage(finalLine.toString());
               }catch (Exception e){

               }
               return message;
           });



          WechatUtil.sendMessage("ZengXiaoYu", "消息发送成功"+back+",现在的时间是:"+System.currentTimeMillis());










          app.close();
      }
}}
