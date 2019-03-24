package com.spring.web3;

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
 * @DATE: [2019/3/8  15:43]
 * @DESC:
 */
@WebServlet(loadOnStartup = 3,name="servlet3",urlPatterns = {"/ser3"}
        ,initParams = {
        @WebInitParam(name="key1",value="value1"),@WebInitParam(name="key2",value="value2")

})
public class Servlet3 extends HttpServlet {
    @Override
    public void init(){
        System.out.println("Servlet3 初始化..");
    }

    private static final AtomicInteger count=new AtomicInteger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do post3 第 "+count.incrementAndGet()+" 次请求");
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do get3 第 "+count.incrementAndGet()+" 次请求");
        super.doPost(req, resp);
    }

}
