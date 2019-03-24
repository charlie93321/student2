package com.spring;

import com.spring.basic.MainConfig;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Iterator;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class AppUtil
{
    static String[] filters=new String[]{
            "environment",
            "systemProperties",
            "systemEnvironment",
            "messageSource",
            "applicationEventMulticaster",
            "lifecycleProcessor"
    };



    public  static void printlnBeans(String[] names, AnnotationConfigApplicationContext app) {

        Iterator<String> iterator=app.getBeanFactory().getBeanNamesIterator();
        if(null==names){
            while(iterator.hasNext()){
                String beanName=iterator.next();
                if(!beanName.contains("org.springframework") && !contains(filters,beanName)) {
                    Object bean = app.getBean(beanName);
                    System.out.println("beanName=[" + beanName + "],bean=[" + bean + "]");
                }
            }
        }else{

            while(iterator.hasNext()){
                String beanName=iterator.next();
                if(contains(names,beanName)) {
                    Object bean = app.getBean(beanName);
                    System.out.println("beanName=[" + beanName + "],bean=[" + bean + "]");
                }
            }


        }
    }



    public  static void printlnAllSingtonBeans( Class clazz,AnnotationConfigApplicationContext app) {
        Map<String, Object> map= app.getBeansOfType(clazz);
        if(null==map || map.isEmpty()) return;
        for (String beanName : map.keySet()) {
              System.out.println(beanName +"="+map.get(beanName));
        }
    }

    private static boolean contains(String[] names, String beanName) {
        if(null==names || StringUtils.isEmpty(beanName)) return false;
        for (String name : names) {
            if(beanName.equals(name)) return true;
        }
        return false;
    }
}
