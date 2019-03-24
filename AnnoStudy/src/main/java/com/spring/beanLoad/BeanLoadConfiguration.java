package com.spring.beanLoad;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/4  17:38]
 * @DESC:
 */
@Configuration
@ComponentScan(basePackages = {"com.spring.beanLoad"})
@Import(value={Teacher.class,MyImportSelector.class,MyImportBeanDefinitionRegister.class})
public class BeanLoadConfiguration {


    /**
     * @Bean 该注解 有 3个特性:
     * 1. 该 注解获得的实例名称 可有 @Bean(name="xxxx") 指定
     *     如果没有指定 则 该实例的名称就是方法的名称
     *
     *
     *
     * */
    @Bean(initMethod = "init12")
    public Person person09(){
       return new Person();
   }

   @Bean(name="person08",initMethod = "init12")
    public Person person01(Person person09){
       Person p8=new Person();
       p8.setId(person09.getId()+100);
       p8.setUserName(person09.getUserName()+"副本");
       p8.setAge(person09.getAge()+100);
       return p8;
   }


    @Bean(initMethod = "init12")
    public Person person11(){
        return new Person();
    }


    @Lazy
    @Bean(initMethod = "init12")
    public Person person12(){
        return new Person();
    }


    @Conditional(value={WindowCondition.class})
    @Bean(initMethod = "init12",name="bill")
    public Person bill(){
        Person bill=new Person();
        bill.setUserName("比尔*盖茨");
        bill.setId(100L);
        bill.setAge(55);
        return bill;
    }

    @Lazy
    @Conditional(value={LinuxCondition.class})
    @Bean(initMethod = "init12",name="linus")
    public Person linus(){
        Person linus=new Person();
        linus.setUserName("李那是");
        linus.setId(101L);
        linus.setAge(58);
        return linus;
    }







}
