package com.servlet.mvc;

import com.servlet.mvc.service.HelloService;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/9  21:06]
 * @DESC:
 */
@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {
    /***
     *
     * 运行时插件  第三方插件的加载
     *
     * 注册 servlet filter listener
     *
     * @param classSet
     * @param sc
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> classSet, ServletContext sc) throws ServletException {

        System.out.println("onStartUp 调用");
        for (Class<?> aClass : classSet) {
                System.out.println("load class:"+aClass.getSimpleName());
        }






    }
}
