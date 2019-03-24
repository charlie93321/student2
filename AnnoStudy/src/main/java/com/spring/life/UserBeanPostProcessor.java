package com.spring.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  13:12]
 * @DESC:
 */
@Component
public class UserBeanPostProcessor implements BeanPostProcessor {

    /**
     *  初始化方法调用之前
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

          System.out.println("BeanPostProcessor==>post_before:"+beanName);

        return bean;
    }

    /**
     *  初始化方法调用之后
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("BeanPostProcessor==>post_after:"+beanName);
        return bean;
    }
}
