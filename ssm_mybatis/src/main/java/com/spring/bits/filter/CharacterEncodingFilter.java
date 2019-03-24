package com.spring.bits.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.spec.XPathType;

import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

//@WebFilter(urlPatterns = {"/*"})
public class CharacterEncodingFilter implements Filter {

	protected void doFilterInternal(
			ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

	    System.out.println("编码过滤器==>"+((HttpServletRequest)request).getRequestURI());
		String encoding = getEncoding();
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		filterChain.doFilter(request, response);
	}

	private String getEncoding() {
		return "utf-8";
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		  System.out.println("编码过滤器过滤");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
           doFilterInternal(request,response,chain);
	}

	@Override
	public void destroy() {
        System.out.println("编码过滤器销毁");
	}
}
