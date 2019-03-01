package com.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TestListener implements ApplicationListener<TestEvent> {


    @Override
    public void onApplicationEvent(TestEvent testEvent) {

        for (int i = 0; i <20; i++) {
            try {
                //System.out.println(".......开始vvv......"+i);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        TestParam param = (TestParam) testEvent.getSource();
      /*  System.out.println(".......开始.......");
        System.out.println("发送邮件:"+param.getEmail());
        System.out.println(".......结束.....");*/
    }
}