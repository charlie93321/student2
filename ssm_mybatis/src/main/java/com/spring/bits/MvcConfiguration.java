package com.spring.bits;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/11  15:49]
 * @DESC:
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@ComponentScan(
        basePackages = {"com.spring.bits"}
        ,includeFilters = {
        @ComponentScan.Filter(type=FilterType.ANNOTATION,classes = {Controller.class})
},useDefaultFilters = false)
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    /**静态资源被拦截时,交给默认的servlet处理*/
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
           configurer.enable();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

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
