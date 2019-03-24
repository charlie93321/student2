package com.asyn.mvc.service;

import com.asyn.mvc.Pt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/11  12:25]
 * @DESC:
 */
@Service
public class AppServiceImpl implements AppService {
    @Override
    public String query(HttpServletRequest req, String userName) throws InterruptedException {

        for (int i = 0; i <3 ; i++) {
              Thread.sleep(1000);
            Pt.pt(req,"睡眠第"+(i+1)+"秒");
        }
        return "Hello to " +userName;
    }

    @Override
    public Integer count(HttpServletRequest req, String userName) throws InterruptedException {

        int sleep=new Random().nextInt(2)+1;
        Thread.sleep(sleep*1000);
        Pt.pt(req,"睡眠第"+(sleep)+"秒");
        return sleep;
    }
}
