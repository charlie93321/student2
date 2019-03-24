package com.spring.web3;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/8  15:51]
 * @DESC:
 */
@WebFilter(urlPatterns = {"/*"}

)
public class AFilter2  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化2");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器放行开始2.............");
        chain.doFilter(request,response);
        System.out.println("过滤器放行结束2.............");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁2");
    }
}

