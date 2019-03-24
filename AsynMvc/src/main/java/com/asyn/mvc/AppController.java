package com.asyn.mvc;

import com.asyn.mvc.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/11  12:07]
 * @DESC:
 */
@Controller
public class AppController{

    @Autowired
    private AppService appService;

    @ResponseBody
    @RequestMapping("/ser1")
    public Callable<String> query(String userName, HttpServletRequest req){

         Pt.pt(req,"controller请求开始");

        Callable<String> result= () -> appService.query(req,userName);


        Pt.pt(req,"controller请求结束");
        return result;
    }
    @ResponseBody
    @RequestMapping("/ser2")
    public String query2(String userName, HttpServletRequest req){

        Pt.pt(req,"controller请求开始");

        List<Callable<Integer>> counts=new ArrayList<>();
        for (int i = 0; i <3; i++) {
            Callable<Integer> result= () -> appService.count(req,userName);
            counts.add(result);
        }
        Integer sum=0;
        for (int i = 0; i < counts.size(); i++) {
            try {
                sum+=counts.get(i).call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        Pt.pt(req,"controller请求结束");
        return "userName=>"+userName+" , money==>"+sum;
    }
}
