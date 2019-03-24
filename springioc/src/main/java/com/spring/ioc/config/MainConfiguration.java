package com.spring.ioc.config;

import com.spring.ioc.vo.Person;
import org.springframework.context.annotation.*;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/1 16:38]
 *  * @DESC:
 *
 *  1. 只有 单例 和 非lazy 的bean 才会被 在容器 初始化 时  创建 并放入容器
 *
 *  2. 多例 和 lazy 加载 必须在 用到时才会创建和加载
 *
 *
 *
 *
 */
@ComponentScan(basePackages = {"com.spring.ioc.basic"})
@Configuration/*配置类*/
public class MainConfiguration {

    @Bean(name="person02",initMethod = "init" ,destroyMethod = "destory" )
    public Person getPerson(){
        Person person=new Person();
        person.setId(2L);
        person.setUserName("tom");
        person.setMessage("这是什么消息");

        return person;
    }

    @Bean(name="person03",initMethod = "init2")
    @Lazy
    public Person getPerson3(){
        Person person=new Person();
        person.setId(3L);
        person.setUserName("jerry");
        person.setMessage("这是什么贵消息");

        return person;
    }

   @Scope(scopeName = "singleton")
    @Bean(name="person04",initMethod = "init2")
    public Person getPerson4(){
        Person person=new Person();
        person.setId(4L);
        person.setUserName("张小凡");
        person.setMessage("单例模式");
        return person;
    }

    @Scope(scopeName = "prototype")
    @Bean(name="person05",initMethod = "init2")
    public Person getPerson5(){
        Person person=new Person();
        person.setId(5L);
        person.setUserName("田灵儿");
        person.setMessage("多例模式");
        return person;
    }
}
