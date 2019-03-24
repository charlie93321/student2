package com.spring.auto;

import org.springframework.context.annotation.*;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  13:39]
 * @DESC:
 */
@Configuration
@ComponentScan(basePackages = {"com.spring.auto"})
public class AutoConfiguration {


    @Bean(name="st01")
    public Student student01(){
        return new Student(1L,"小凡","三年级","中国");
    }

    @Primary
    @Bean(name="st02")
    public Student student02(){
        return new Student(2L,"灵儿","四年级","中国");
    }


    @Profile("dev")
    @Bean(name="stDev")
    public Student stDev(){
        return new Student(3L,"灵儿dev","四年级","中国");
    }

    @Profile("test")
    @Bean(name="stTest")
    public Student stTest(){
        return new Student(4L,"灵儿test","四年级","中国");
    }
}
