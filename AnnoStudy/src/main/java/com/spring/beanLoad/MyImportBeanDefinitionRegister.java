package com.spring.beanLoad;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  11:15]
 * @DESC:
 */
public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //Class<?> beanClass, ConstructorArgumentValues cargs, MutablePropertyValues pvs
        ArrayList<PropertyValue> pvList=new ArrayList<PropertyValue>(0);
        pvList.add(new PropertyValue("id","1000"));
        pvList.add(new PropertyValue("userName","路招摇"));
        pvList.add(new PropertyValue("age","23"));
        MutablePropertyValues pvs=new MutablePropertyValues(pvList);
        BeanDefinition beanDefinition=new RootBeanDefinition(Person.class,null,pvs);
        registry.registerBeanDefinition("personForeign",beanDefinition);
    }
}
