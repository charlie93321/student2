package com.asyn.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/10  21:54]
 * @DESC:
 */
public class AppUtil {

    public static void pt(HttpServletRequest req, String message){
        System.out.println(req.getRequestURI()
                +message+",线程:"+Thread.currentThread().getName()+",时间:"+System.currentTimeMillis());

    }
}
