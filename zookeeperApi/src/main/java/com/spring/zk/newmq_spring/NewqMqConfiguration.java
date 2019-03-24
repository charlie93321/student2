package com.spring.zk.newmq_spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.annotation.Transactional;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/14  16:14]
 * @DESC:
 */
@Configuration
@PropertySource(value = {"classpath:mq.properties"})
@ComponentScan(basePackages = {"com.spring.zk.newmq_spring"})
public class NewqMqConfiguration {







}
