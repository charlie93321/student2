package com.spring.web3;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/7  20:58]
 * @DESC:
 */

@WebFilter(urlPatterns = {"/*"}

)
public class BFilter1  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
          System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器放行开始.............");
        chain.doFilter(request,response);
        System.out.println("过滤器放行结束.............");
    }

    @Override
    public void destroy() {
             System.out.println("过滤器销毁");
    }
}
