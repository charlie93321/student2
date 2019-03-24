package com.asyn.mvc;

import javax.servlet.http.HttpServletRequest;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/11  12:23]
 * @DESC:
 */
public class Pt {
    public static void pt(HttpServletRequest req, String message){
        System.out.println(req.getRequestURI()
                +message+",线程:"+Thread.currentThread().getName()+",时间:"+System.currentTimeMillis());

    }
}
