package com.asyn.mvc.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/11  12:20]
 * @DESC:
 */
public interface AppService {
    String query(HttpServletRequest req, String userName) throws InterruptedException;

    Integer count(HttpServletRequest req, String userName) throws InterruptedException;
}
