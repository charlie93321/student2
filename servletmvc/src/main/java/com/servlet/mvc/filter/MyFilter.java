package com.servlet.mvc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/10  19:05]
 * @DESC:
 */
@WebFilter("/*")/*拦截所有的请求*/
public class MyFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器===>"+((HttpServletRequest)request).getRequestURI());
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
