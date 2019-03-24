package com.spring.beanLoad;

import com.spring.AppUtil;
import com.spring.UnSafeUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/4  17:36]
 * @DESC:
 */
public class TestBeanLoad {


      public static void main(String[] args) throws Throwable {

          AnnotationConfigApplicationContext  app=new AnnotationConfigApplicationContext(BeanLoadConfiguration.class);



            System.out.println("----------------------------");
            AppUtil.printlnBeans(null,app);
          app.close();

      }
}
