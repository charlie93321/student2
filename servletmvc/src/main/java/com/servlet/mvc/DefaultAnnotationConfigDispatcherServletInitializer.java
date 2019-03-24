package com.servlet.mvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/10  15:33]
 * @DESC:
 */
public class DefaultAnnotationConfigDispatcherServletInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer{


    /**
     * root - spring config 配置类  父容器
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootConfiguration.class
        };
    }

    /**
     * springmvcIOC 容器  子容器
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                ServletConfiguration.class
        };
    }

    /**
     *    > "/" 表示 拦截所用的请求包括静态资源 单不包括 jsp页面
     *
     *    > "/*" 表示拦截所有的请求,包括jsp页面
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
