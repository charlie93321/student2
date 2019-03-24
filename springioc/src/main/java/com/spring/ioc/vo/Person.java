package com.spring.ioc.vo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

import java.util.Objects;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/1  16:41]
 * @DESC:
 */
public class Person implements BeanNameAware ,BeanFactoryAware,InitializingBean,DisposableBean,SmartInitializingSingleton {
    private Long id;
    private String userName;
    private String  message;

    public Person() {
         System.out.println("构造方法调用.......");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        System.out.println(this+"对象setter方法调用==>属性注入");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(userName, person.userName) &&
                Objects.equals(message, person.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, message);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Person{");
        sb.append("id=").append(id);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }

    private void init() {
          System.out.println(this+"===>初始化");
    }


    private void destory() {
        System.out.println(this+"===>销毁方法");
    }

    private void init2() {
        System.out.println(this+"===>初始化2");
    }


    public String  beanName;
    @Override
    public void setBeanName(String beanName) {
          System.out.println("beanName注入");
          this.beanName=beanName;
    }

    public BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("beanFactory注入");
        this.beanFactory=beanFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
          System.out.println("set设置完毕调用");
    }

    @Override
    public void destroy() throws Exception {
          System.out.println("对象销毁时调用");
    }

    @Override
    public void afterSingletonsInstantiated() {
          System.out.println("after singletonInstant");
    }
}
/**
 **
 * 容器启动时：
 *
 * 最先调用
 *
 *         BeanFactoryPostProcessor
 *
 *                         ->postProcessBeanFactory()
 *
 *
 *
 * getBean时：
 *
 * 实例化之后调用：
 *
 *         InstantiationAwareBeanPostProcessor
 *
 *                 ->postProcessPropertyValues()
 *
 *
 *
 * 初始化时：
 *
 *         属性注入（setter）
 *
 *
 *
 *         BeanNameAware
 *
 *                 ->setBeanName()
 *
 *         BeanFactoryAware
 *
 *                 ->setBeanFactory()
 *
 *         ApplicationContextAware
 *
 *                 ->setApplicationContext()
 *
 *
 *
 *         BeanPostProcessor
 *
 *                ->postProcessBeforeInitialization()
 *
 *         InitializingBean
 *
 *                 ->afterPropertiesSet()
 *
 *         init-method属性
 *
 *         BeanPostProcessor
 *
 *                 ->postProcessAfterInitialization()
 *
 *
 *
 *         DiposibleBean
 *
 *                 ->destory()
 *
 *         destroy-method属性
 */