package com.spring;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Test {
    
    @Async(value = "logExecutor")
    public void testAsyncMethod(String taskName){
         // System.out.println("开始执行异步任务:"+taskName);
        try {
            //让程序暂停100秒，相当于执行一个很耗时的任务
            for (int i = 0; i <1 ; i++) {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"异步任务执行中:"+taskName);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            //System.out.println("异步任务执行完毕:"+taskName);
        }
    }
}