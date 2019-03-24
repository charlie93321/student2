package com.spring.auto;

import com.spring.AppUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  16:36]
 * @DESC:
 */
public class AutoMain {


      public static void main(String[] args){


          AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(AutoConfiguration.class);

          AppUtil.printlnBeans(null,app);

          app.close();
      }
}
