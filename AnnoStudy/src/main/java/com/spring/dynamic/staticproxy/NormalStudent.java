package com.spring.dynamic.staticproxy;

import lombok.Data;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/6  11:48]
 * @DESC:
 */
@Data
public class NormalStudent implements  Student {

    @Override
    public void sendFee(String userName) {
          System.out.println(userName+"交给班长班费:$"+50);
    }

    @Override
    public String toString() {
       return "com.spring.dynamic.staticproxy.NormalStudent";
    }
}
