package com.spring.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  17:30]
 * @DESC:
 */
@Configuration
@ComponentScan(basePackages = {"com.spring.aop"})
@EnableAspectJAutoProxy
public class AopConfiguration {


    @Bean(name="normal")
    public NormalClass normalClass(){
        return new NormalClass();
    }


    @Bean(name="aop")
    public AopClass aopClass(){
        return new AopClass();
    }

}
