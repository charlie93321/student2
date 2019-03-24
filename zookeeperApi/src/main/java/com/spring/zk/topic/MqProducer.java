package com.spring.zk.topic;

import lombok.Getter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang3.StringUtils;

import javax.jms.*;

public class MqProducer{
        private Connection connection;
        @Getter
        private Session session;
        private MessageProducer producer;
        private Destination bindDestination;
        private Destination defaultDestination;
        private boolean transacted=false;

        public MqProducer(MqConfiguration configuration) throws JMSException {
           init(configuration);
        }

        private void init(MqConfiguration config) throws JMSException {
            ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(
                    config.getUserName(),config.getPassword(),config.getUrl());
            connection=connectionFactory.createConnection();

            session=connection.createSession(config.isTransacted(),config.getAcknowledgeMode());
            if(StringUtils.isNotEmpty(config.getDestination()))
                                     bindDestination=session.createTopic(config.getDestination());
            producer=session.createProducer(bindDestination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            transacted=config.isTransacted();
            connection.start();
            System.out.println("MqProducer 初始化....");
        }
        
        public void sendMessage(Destination destination,Message...  messages){
            if(null==messages || messages.length==0) return;
            try {
                for (Message message : messages) {
                    if (null == destination) {
                         if(null==bindDestination)
                              producer.send(getDefaultDestination(),message);
                         else producer.send(message);
                    } else
                        producer.send(destination, message);
                }
                if(transacted)session.commit();
            } catch (JMSException e) {
                if(transacted) {
                    try {
                          System.out.println("执行消息回退....");
                        session.rollback();
                    } catch (JMSException e1) {
                        e1.printStackTrace();
                    }
                }
                e.printStackTrace();
                
            } finally {
                try {
                    release();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

        private void release() throws JMSException {
            if(null!=producer)producer.close();
            if(null!=session)session.close();
            if(null!=connection)connection.close();
        }


        synchronized  Destination  getDefaultDestination() throws JMSException {
            while (null==defaultDestination)
               defaultDestination=session.createTopic("default-topic");
            return defaultDestination;
        }
    }