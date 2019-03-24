package com.asyn.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/10  21:53]
 * @DESC: asyncSupported = true 支持异步
 */
@WebFilter(urlPatterns = {"/*"},asyncSupported = true)
public class Filter1 implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
          System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            AppUtil.pt((HttpServletRequest) request,"过滤器开始过滤");

        /**
         * 请求中文乱码
         */
        request.setCharacterEncoding("UTF-8");

        /**
         * 响应 中文乱码
          */
        HttpServletResponse resp= (HttpServletResponse) response;
        resp.setContentType("text/html;charset=utf-8");

        chain.doFilter(request,resp);

        AppUtil.pt((HttpServletRequest) request,"过滤器结束过滤");
    }

    @Override
    public void destroy() {
             System.out.println("过滤器销毁");
    }
}
