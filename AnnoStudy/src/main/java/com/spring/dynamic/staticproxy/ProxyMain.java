package com.spring.dynamic.staticproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/6  12:01]
 * @DESC:
 */
public class ProxyMain {

      public static void main(String[] args){

            //staticProxy();
            dynamicProxy();
            //cglibProxy();

      }

    private static void cglibProxy() {

        NormalStudent student = (NormalStudent) new StudentCglibProxy().getInstance(new NormalStudent());


        student.sendFee("小张");
        student.sendFee("小明");
        student.sendFee("小红");
        student.sendFee("小亮");

    }

    private static void dynamicProxy() {
        InvocationHandler handler = new StudentProxy(new NormalStudent());
        Student student = (Student) Proxy.newProxyInstance(
                handler.getClass().getClassLoader(), new Class[]{Student.class} , handler);

        student.sendFee("小张");
        student.sendFee("小明");
        student.sendFee("小红");
        student.sendFee("小亮");
    }

    private static void staticProxy() {
        Student student=new MonitorStudent(new NormalStudent());

             student.sendFee("小张");
             student.sendFee("小明");
             student.sendFee("小红");
             student.sendFee("小亮");
    }
}
