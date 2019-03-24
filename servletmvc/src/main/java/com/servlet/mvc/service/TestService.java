package com.servlet.mvc.service;

import org.springframework.stereotype.Service;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/10  15:46]
 * @DESC:
 */
@Service
public class TestService {


    public String sayHello(String userName) {

        return " say hello to "+userName+"!";
    }
}
