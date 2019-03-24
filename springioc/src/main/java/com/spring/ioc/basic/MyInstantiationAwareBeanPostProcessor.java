package com.spring.ioc.basic;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

@Component
public class MyInstantiationAwareBeanPostProcessor extends
        InstantiationAwareBeanPostProcessorAdapter {

    public MyInstantiationAwareBeanPostProcessor() {
        super();
        System.out
                .println("MyInstantiationAwareBeanPostProcessor构造器");
    }

    // 接口方法、实例化Bean之前调用
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass,
            String beanName) throws BeansException {
        if("person02".equals(beanName))
        System.out
                .println("InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法");
        return null;
    }

    // 接口方法、实例化Bean之后调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if("person02".equals(beanName))
        System.out
                .println("InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法");
        return bean;
    }

    // 接口方法、设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs,
            PropertyDescriptor[] pds, Object bean, String beanName)
            throws BeansException {
        if("person02".equals(beanName))
                  System.out.println("设置某个属性时调用===>"+ Arrays.asList(pvs));
        return pvs;
    }
}