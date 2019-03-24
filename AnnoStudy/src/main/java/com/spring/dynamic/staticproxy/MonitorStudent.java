package com.spring.dynamic.staticproxy;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/6  11:50]
 * @DESC:
 */
@Data
public class MonitorStudent implements Student {

    private Student student;

    public MonitorStudent(Student student) {
        this.student = student;
    }



    private static final  AtomicInteger total=new AtomicInteger(0);
    @Override
    public void sendFee(String userName) {
           student.sendFee(userName);
           System.out.println("班长收到班费总计:$"+total.addAndGet(50));
           if(total.get()==200){
                 System.out.println("班费收齐,上交班费给老师");
           }
    }
}
