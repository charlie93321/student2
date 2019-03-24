package com.spring.basic;

import com.spring.AppUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Iterator;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/4  17:13]
 * @DESC:
 */
public class TestAnno {





    @Test
    public void testValue(){

        AnnotationConfigApplicationContext app
                =new AnnotationConfigApplicationContext(MainConfig.class);

        AppUtil.printlnBeans(null,app);

        app.close();


    }
}
