package com.spring.web3;

import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/7  19:26]
 * @DESC:
 */

@WebServlet(loadOnStartup = 1,name="servlet1",urlPatterns = {"/ser1"}
        ,initParams = {
        @WebInitParam(name="key1",value="value1"),@WebInitParam(name="key2",value="value2")

})
public class Servlet1 extends HttpServlet {

    @Override
    public void init(){
          System.out.println("Servlet1 初始化..");
    }

    private static final AtomicInteger count=new AtomicInteger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do post 第 "+count.incrementAndGet()+" 次请求");
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do get 第 "+count.incrementAndGet()+" 次请求");
        super.doPost(req, resp);
    }



}
