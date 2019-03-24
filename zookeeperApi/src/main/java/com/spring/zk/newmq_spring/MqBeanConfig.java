package com.spring.zk.newmq_spring;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/14  16:16]
 * @DESC:
 */
@Component
public class MqBeanConfig {
    @Value("${mq.url}")
    private String url;
    @Value("${mq.max.connection}")
    private Integer maxPoolConnections;
    @Value("${mq.session.cache.size}")
    private Integer sessionCacheSize;
    @Value("${mq.default.queue.clientId}")
    private String defaultQueueClientId;

    @Value("${mq.default.topic.clientId}")
    private String defaultTopicClientId;
    @Value("${mq.default.queue.destination}")
    private String defaultQueueDestinationName;

    @Value("${mq.default.topic.destination}")
    private String defaultTopicDestinationName;

    @Bean
    public ActiveMQConnectionFactory mqConnectionFactory(){

        ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);

        return connectionFactory;
    }


    @Bean
    public PooledConnectionFactory pooledConnectionFactory(ActiveMQConnectionFactory mqConnectionFactory){

        PooledConnectionFactory connectionFactory=new PooledConnectionFactory(mqConnectionFactory);
        connectionFactory.setMaxConnections(maxPoolConnections);
        return connectionFactory;
    }


    @Bean
    public CachingConnectionFactory connectionFactory(PooledConnectionFactory pooledConnectionFactory){

        CachingConnectionFactory connectionFactory=new CachingConnectionFactory(pooledConnectionFactory);
        /**
         *  1次 发送/接收 多少条记录
         */
        connectionFactory.setSessionCacheSize(sessionCacheSize);


        return connectionFactory;
    }


    @Bean
    public JmsTransactionManager jmsTransactionManager(CachingConnectionFactory connectionFactory){
        JmsTransactionManager jmsTransactionManager=new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(connectionFactory);
        return jmsTransactionManager;
    }


    @Bean
    public Destination defaultQueueDestination(){
        return new ActiveMQQueue(defaultQueueDestinationName);
    }

    @Bean
    public Topic defaultTopicDestination(){
        return new ActiveMQTopic(defaultQueueDestinationName);
    }

    /**
     * <!-- 消息监听容器 消息接收监听器用于异步接收消息 -->
     * 	<bean id="jmsContainerOne" class="org.springframework.jms.listener.DefaultMessageListenerContainer">
     * 		<property name="connectionFactory" ref="connectionFactory" />
     * 		<property name="destination" ref="destinationOne" />
     * 		<property name="messageListener" ref="consumerMessageListenerOfOne" />
     * 		<!-- <property name="sessionTransacted" value="true"/> -->  <!-- 给listener添加事务，只负责接收消息的回滚 （有了transactionManager就不用这个了，这个功能不全） 设置后好像并没有起作用 不知道为啥 -->
     * 		<!-- <property name="transactionManager" ref="jtaTransactionManager"/> --> <!-- 接收消息和数据库访问处于同一事务中 jta -->
     * 		<property name="transactionManager" ref="jmsTransactionManager" /> <!--jms事务 -->
     * 		<property name="sessionAcknowledgeMode" value="4"></property>   <!-- 应答模式是 INDIVIDUAL_ACKNOWLEDGE http://blog.csdn.net/yueding_h/article/details/54944254 -->
     * 		<!-- ActiveMQ:设置多个并行的消费者 -->
     * 		<property name="concurrency" value="2-3" />
     * 	</bean>
     */

    @Bean(name="queueListenerContainer")
    public DefaultMessageListenerContainer defaultMessageListenerContainer
    (CachingConnectionFactory connectionFactory,
     @Qualifier("defaultListener") SessionAwareMessageListener<ActiveMQTextMessage> messageListener,
     Destination defaultQueueDestination,
     JmsTransactionManager jmsTransactionManager){

        DefaultMessageListenerContainer container=new DefaultMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);

        container.setClientId(defaultQueueClientId);

        container.setMessageListener(messageListener);


        container.setPubSubDomain(false);


        container.setDestination(defaultQueueDestination);

        container.setTransactionManager(jmsTransactionManager);

        container.setSessionTransacted(true);

        container.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);

        container.setConcurrency("2-3");

        return container;
    }


    @Bean(name="TopicListenerContainer")
    public DefaultMessageListenerContainer topicListenerContainer
            (CachingConnectionFactory connectionFactory,
             @Qualifier("topicListener") MessageListener topicListener,
             Topic defaultTopicDestination,
             JmsTransactionManager jmsTransactionManager){

        DefaultMessageListenerContainer container=new DefaultMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);

        container.setClientId(defaultTopicClientId);

        container.setMessageListener(topicListener);


        container.setPubSubDomain(true);


        container.setDestination(defaultTopicDestination);

        container.setTransactionManager(jmsTransactionManager);

        container.setSessionTransacted(true);

        container.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);


        return container;
    }



    @Bean(name="template")
    @Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public JmsTemplate jmsTemplate(CachingConnectionFactory connectionFactory){

        JmsTemplate jmsTemplate=new JmsTemplate(connectionFactory);

       // jmsTemplate.setDefaultDestination(defaultQueueDestination());

        jmsTemplate.setReceiveTimeout(3000);


        //jmsTemplate.setPubSubDomain(false);

        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);

        jmsTemplate.setSessionTransacted(true);

        jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);

        return jmsTemplate;
    }


    @Bean(name="topicTemplate")
    public JmsTemplate topicTemplate(JmsTemplate template,Destination defaultTopicDestination){

        template.setDefaultDestination(defaultTopicDestination);
        template.setPubSubDomain(true);
        return template;
    }

    @Bean(name="queueTemplate")
    public JmsTemplate queueTemplate(JmsTemplate template,Destination defaultQueueDestination){
        template.setDefaultDestination(defaultQueueDestination);
        template.setPubSubDomain(false);
        return template;
    }
}
