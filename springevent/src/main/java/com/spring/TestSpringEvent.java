package com.spring;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/28  22:03]
 * @DESC:
 */
public class TestSpringEvent {


    @Test
    public  void testEvent1(){

        ClassPathXmlApplicationContext app=new ClassPathXmlApplicationContext("classpath:spring-event.xml");


        TestPublish publish=app.getBean(TestPublish.class);
        TestParam source=new TestParam();
        source.setEmail("32411235@qq.com");
        publish.publishEvent(new TestEvent(source));



        app.publishEvent(new TestEvent(source));
        app.close();
    }

    /**
     *  在业务中实现 异步 解偶  利用 spring 事件异步机制
     *
     * @throws InterruptedException
     */
    @Test
    public  void testEvent2() throws InterruptedException {

        ClassPathXmlApplicationContext app=new ClassPathXmlApplicationContext("classpath:spring-event.xml");

        UserviceImpl userservice=app.getBean(UserviceImpl.class);

        userservice.register("3253534@qq.com");

        app.close();
    }
}
