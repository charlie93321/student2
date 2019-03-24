package com.spring;

import com.spring.ioc.config.MainConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext applicationContext
                =new AnnotationConfigApplicationContext(MainConfiguration.class);
        applicationContext.close();
    }
}
