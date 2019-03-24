package com.spring.life;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  11:31]
 * @DESC:
 */


@Configuration
@ComponentScan(basePackages = {"com.spring.life"})
public class BeanLifeConfiguration {


    @Bean(initMethod = "init_bean" ,destroyMethod = "destory_bean")
    public User user01(){
       User u01=new User();

       u01.setUserName("U01");
        u01.setBirthDay(Date.valueOf("2018-12-12"));
        return u01;
    }


}
