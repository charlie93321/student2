package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/1  12:23]
 * @DESC:
 */
@Service
public class UserviceImpl {
    @Autowired
    private TestPublish testPublish;

    public void register(String email) throws InterruptedException {
        System.out.println("開始註冊");
        for (int i = 0; i <5 ; i++) {
            Thread.sleep(300);
            System.out.println("註冊.....");
        }
        System.out.println("注册完毕.....");
        testPublish.publishEvent(new TestEvent(new TestParam().setEmail(email)));
        System.out.println("注册完毕返回.....");

    }


    @EventListener(classes = {TestEvent.class})
    public void handEvent(ApplicationEvent event) throws InterruptedException {
          System.out.println("邮件通知==>"+event);
        for (int i = 0; i <5 ; i++) {

            System.out.println("邮件发送中.....");
        }

        System.out.println("邮件通知完毕==>"+event);


    }
}
