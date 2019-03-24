package com.spring.life;

import com.spring.AppUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  11:35]
 * @DESC:
 */
public class Main {

      public static void main(String[] args){

             AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(BeanLifeConfiguration.class);

             AppUtil.printlnBeans(null,app);


               System.out.println("\r\n\r\n");
             app.close();
      }
}
