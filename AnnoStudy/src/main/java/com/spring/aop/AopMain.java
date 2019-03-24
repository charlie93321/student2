package com.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  18:57]
 * @DESC:
 */
public class AopMain {

      public static void main(String[] args){
          AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(AopConfiguration.class);

          NormalClass normal=app.getBean(NormalClass.class);

          normal.div(10,2);
  System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
         normal.div(10,0);
          app.close();

      }
}
