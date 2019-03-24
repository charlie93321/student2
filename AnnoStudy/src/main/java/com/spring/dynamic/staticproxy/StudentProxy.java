package com.spring.dynamic.staticproxy;

import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/6  12:15]
 * @DESC:
 */
@Data
public class StudentProxy implements InvocationHandler {
   // private Student student;
   private final AtomicInteger total=new AtomicInteger(0);
    private Object target;
    public StudentProxy(Object target){
        this.target=target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        Object result=method.invoke(target,args);

        if("sendFee".equals(method.getName())) {
            System.out.println("班长收到班费总计:$" + total.addAndGet(50));
            if(total.get()==200)  System.out.println("班费收齐,上交班费给老师");

        }
        return result;
    }
}
