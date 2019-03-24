package com.asyn.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/10  21:46]
 * @DESC:  asyncSupported = true 支持异步servlet
 */
@WebServlet(name="servlet1",urlPatterns = {"/ser1"},
        asyncSupported = true)
public class Servlet1 extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppUtil.pt(req,"请求开始");

        AsyncContext context=req.startAsync();

        context.start(()->{
              AppUtil.pt(req,"异步开始");
              try {
                  Thread.sleep(3000);
                  context.complete();
                  ServletResponse response = context.getResponse();
                  try {
                      response.getOutputStream()
                              .write("异步成功返回".getBytes("utf-8"));
                  }catch (IOException e) {
                      e.printStackTrace();
                  }
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              AppUtil.pt(req,"异步结束");
        });
        AppUtil.pt(req,"请求结束");
    }



}
