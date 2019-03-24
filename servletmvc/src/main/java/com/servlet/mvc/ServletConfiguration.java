package com.servlet.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/10  15:40]
 * @DESC: useDefaultFilters = false 禁用默认的过滤器
 */
@ComponentScan(basePackages = "com.servlet.mvc" , includeFilters = {
        @ComponentScan.Filter(type=FilterType.ANNOTATION,value={org.springframework.stereotype.Controller.class})
},useDefaultFilters = false)
@EnableWebMvc
public class ServletConfiguration  implements WebMvcConfigurer {


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    /**
     * 配置默认的servletHand
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        /**
         *
         *此时会注册一个默认的Handler：DefaultServletHttpRequestHandler，
         * 这个Handler也是用来处理静态文件的，它会尝试映射/*。
         * 当DispatcherServelt映射/时（/ 和/* 是有区别的），
         * 并且没有找到合适的Handler来处理请求时，
         * 就会交给DefaultServletHttpRequestHandler 来处理。
         * 注意：这里的静态资源是放置在web根目录下，而非WEB-INF 下。
         *
         * 设置servlet 拦截所有资源后"/*"
         *  如果不配置这个,访问jsp页面报找不到页面的错误
         *
         * 被拦截的静态资源 ,配置后就可以被访问了
         *  但是 浏览器呈现的是jsp的源码
         *
         *
          */
        configurer.enable();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }


 //<mvc:interceptors>
    //<mvc:interceptor>
    // <mvc:mapping path="/**/*"/>
    //   <mvc:exclude-mapping path="/**/fonts/*"/>
    //  <mvc:exclude-mapping path="/**/*.css"/>
    //  <mvc:exclude-mapping path="/**/*.js"/>
    // <mvc:exclude-mapping path="/**/*.png"/>
    // <mvc:exclude-mapping path="/**/*.gif"/>
    // <mvc:exclude-mapping path="/**/*.jpg"/>
    // <mvc:exclude-mapping path="/**/*.jpeg"/>
    // <mvc:exclude-mapping path="/**/*login*"/>
    // <mvc:exclude-mapping path="/**/*Login*"/>
    // <bean class="com.luwei.console.mg.interceptor.VisitInterceptor"></bean>
    //</mvc:interceptor>
//</mvc:interceptors>
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
          registry.addInterceptor(new MyHandlerInterceptor())
                  .addPathPatterns("/**");
    }

    /**
     * 被拦截的静态资源 , 允许方法 的配置项
     *
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       /*  registry
                  .addResourceHandler("/imgs/**")
                  .addResourceLocations("/imgs/");

        registry
                .addResourceHandler("/views/**")
                .addResourceLocations("/views/");*/

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    /**
     * 配置视图解析器 反倒不用配
     * <!-- 配置视图解析器 -->
     *     <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     *         <property name="prefix" value="/WEB-INF/jsp/"/>
     *         <property name="suffix" value=".jsp"/>
     *     </bean>
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        //registry.viewResolver(new InternalResourceViewResolver());
       //registry.jsp("/WEB-INF/views/",".jsp");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
