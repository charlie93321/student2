package com.asyn.mvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/11  12:09]
 * @DESC:
 */
public class MvcWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    /**
     * root 容器 的配置类
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfiguration.class};
    }

    /**
     * springmvc 容器的配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfiguration.class};
    }

    /**
     *  拦截 的路径  对所有资源的拦截配置
     *  /*
     *  /
     *  *.do  之类的
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
