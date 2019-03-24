package com.spring.ioc.basic;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        super();
        System.out.println("这是BeanPostProcessor实现类构造器！！");
        // TODO Auto-generated constructor stub
    }

    /**
     *  实例化之后的处理
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if("person02".equals(beanName)) System.out.println("实例化之后的处理==>"+beanName);
        return bean;
    }

    /**
     *  实例化之前的处理
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        if("person02".equals(beanName)) System.out.println("实例化之前的处理===>"+beanName);
        return bean;
    }
}