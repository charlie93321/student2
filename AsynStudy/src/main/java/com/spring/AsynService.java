package com.spring;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/1  14:22]
 * @DESC:
 */
@Service
public class AsynService {

    private static final Random random=new Random();
    @Async(value="logExecutor")
    public Future<Result> getResult(String taskName) throws InterruptedException {
        int count=0;
        for (int i = 0; i <10 ; i++) {
            if("任务3".equals(taskName))Thread.sleep(600);
            else Thread.sleep(300);
            count+=random.nextInt(10+i);
        }
          System.out.println(taskName+"线程:"+Thread.currentThread().getName()+"返回"+count);
        Result result=new Result();
        result.setCount(count);
        result.setTaskName(taskName);
        return new AsyncResult<Result>(result);
    }
}
