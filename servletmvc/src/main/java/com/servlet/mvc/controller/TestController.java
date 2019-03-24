package com.servlet.mvc.controller;

import com.servlet.mvc.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/10  15:45]
 * @DESC:
 */
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping("/hello")
    public String  sayHello(String userName){

        System.out.println("调用controller...");


        return testService.sayHello(userName);
    }


    @RequestMapping("/test")
    public String test(String userName, HttpServletRequest request){
        //ModelAndView modelAndView=new ModelAndView();
        System.out.println("调用controller...");


        //modelAndView.setViewName("test");
        //modelAndView.addObject("userName",userName);

       request.setAttribute("userName",userName);

       return "/WEB-INF/views/test.jsp";
    }


    @RequestMapping("/user")
    public String user(String userName, HttpServletRequest request){
        System.out.println("调用controller...");
        request.setAttribute("userName",userName);
        return "/WEB-INF/views/user.html";
    }

    @RequestMapping("/user2")
    public ModelAndView user2(String userName, HttpServletRequest request){
        System.out.println("调用controller...");
       return  new ModelAndView("/WEB-INF/views/user.html",
               Collections.singletonMap("userName",userName));
    }

}
