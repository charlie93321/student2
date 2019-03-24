package com.spring.zk.topic;

import lombok.Getter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang3.StringUtils;

import javax.jms.*;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/13  18:20]
 * @DESC:
 */
public class MqConsumer {
        private Connection connection;
        @Getter
        private Session session;
        @Getter
        private MessageConsumer consumer;
        private Topic bindDestination;
    /**
     *     AUTO_ACKNOWLEDGE = 1    自动确认
     *     CLIENT_ACKNOWLEDGE = 2    客户端手动确认
     *     DUPS_OK_ACKNOWLEDGE = 3    自动批量确认
     *     SESSION_TRANSACTED = 0    事务提交并确认
     */
    private int  acknowledgeMode=Session.CLIENT_ACKNOWLEDGE;

        public MqConsumer(MqConfiguration configuration,String clientId,String sqlCondition) throws JMSException {
            init(configuration,clientId,sqlCondition);
        }

        private void init(MqConfiguration config,String clientId,String sqlCondition) throws JMSException {
            ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(
                    config.getUserName(),config.getPassword(),config.getUrl());
            connection=connectionFactory.createConnection();
            connection.setClientID(clientId);
            session=connection.createSession(config.isTransacted(),config.getAcknowledgeMode());
            if(StringUtils.isNotEmpty(config.getDestination()))
                bindDestination=session.createTopic(config.getDestination());
            if(bindDestination==null)bindDestination=getDefaultDestination();
            consumer=session.createDurableSubscriber(bindDestination,"xxx");
            acknowledgeMode=config.getAcknowledgeMode();
            connection.start();
            System.out.println("MqConsumer 初始化....");
        }

        public void receive(MessageListener listener){
            try {

                while (true)
                    consumer.setMessageListener(listener);
            } catch (JMSException e) {
                e.printStackTrace();
            }finally {
                try {
                    release();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }



    private void release() throws JMSException {
            if(null!=consumer)consumer.close();
            if(null!=session)session.close();
            if(null!=connection)connection.close();
        }


          Topic  getDefaultDestination() throws JMSException {
            return session.createTopic("default-topic");
        }

}
