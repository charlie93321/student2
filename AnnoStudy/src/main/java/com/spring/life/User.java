package com.spring.life;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.Date;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  11:32]
 * @DESC:
 */
@Data
public class User implements InitializingBean ,DisposableBean,ApplicationContextAware {
     private String userName;
     private Date birthDay;


    public User() {
         System.out.println("User 构造方法");
    }

    @PostConstruct
    public void postConstruct(){
          System.out.println(" @PostConstruct构造器方法之后调用");
    }

    @PreDestroy
    public void preDestory(){
          System.out.println("@PreDestroy在销毁方法调用之前调用");
    }

    private void init_bean() {
            System.out.println("@Bean指定初始化方法");
     }

    private void destory_bean() {
        System.out.println("@Bean指定销毁方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
          System.out.println("InitializingBean指定的初始化方法");
    }

    @Override
    public void destroy() throws Exception {
          System.out.println("DisposableBean 指定的销毁方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
          System.out.println("ApplicationContextAware 注入:"+applicationContext);
    }
}
