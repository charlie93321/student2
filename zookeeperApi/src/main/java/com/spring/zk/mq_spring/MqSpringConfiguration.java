package com.spring.zk.mq_spring;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import java.awt.event.ContainerListener;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/13  22:56]
 * @DESC:
 */
@Configuration
@ComponentScan(basePackages = {"com.spring.zk.mq_spring"})
@EnableJms/** 开启jms 注解开发 */
public class MqSpringConfiguration {

    /**
     * 原始的mq 的连接的工厂
     * @return
     */
     @Bean(name="activeMQConnectionFactory")
     public ActiveMQConnectionFactory mqFactory(){
         String brokerURL="failover:(tcp://hadoop01:61616,tcp://hadoop02:61616,tcp://hadoop03:61616)?Randomize=false";
         return new ActiveMQConnectionFactory(null,null,brokerURL);
     }

    /**
     *  池 技术
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean(name="pooledConnectionFactory")
     public PooledConnectionFactory pooledConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory){
         PooledConnectionFactory pooledConnectionFactory=new PooledConnectionFactory(activeMQConnectionFactory);
         pooledConnectionFactory.setMaxConnections(15);
         return pooledConnectionFactory;
     }



     @Bean(name="connectionFactory")
     public CachingConnectionFactory cachingConnectionFactory(PooledConnectionFactory pooledConnectionFactory){

         CachingConnectionFactory connectionFactory=new CachingConnectionFactory(pooledConnectionFactory);

         connectionFactory.setSessionCacheSize(5);

         return connectionFactory;
     }


     @Bean(name="defaultQueue")
     public Destination queue(){
         return new ActiveMQQueue("topic-t-01");
     }


    @Bean(name="jmsTemplate")
    public JmsTemplate jmsTemplate(SingleConnectionFactory singleConnectionFactory ){

        JmsTemplate jmsTemplate=new JmsTemplate(singleConnectionFactory);

        /** 队列  */
        jmsTemplate.setPubSubDomain(false);

        jmsTemplate.setMessageConverter(new
                SimpleMessageConverter());

        jmsTemplate.setDefaultDestinationName("topic-test-01");

        jmsTemplate.setReceiveTimeout(2000);


        return jmsTemplate;
    }


    @Bean(name = "jmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory
            (CachingConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory  containerFactory=new DefaultJmsListenerContainerFactory();

        containerFactory.setConnectionFactory(connectionFactory);
        return containerFactory;
    }

    @Bean(name = "messageListenerContainer")
    public org.springframework.jms.listener.DefaultMessageListenerContainer MessageContainer
            (CachingConnectionFactory connectionFactory){
        DefaultMessageListenerContainer container=new DefaultMessageListenerContainer();
        container.setDestinationName("topic-test-01");
        container.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        //container.setTransactionManager();
        //container.setTaskExecutor();
        container.setPubSubDomain(true);
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                  System.out.println("第二次接收到:"+message);
            }
        });
        return container;
    }


}
