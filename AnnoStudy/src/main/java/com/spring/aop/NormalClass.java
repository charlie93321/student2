package com.spring.aop;

import java.math.BigDecimal;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  18:23]
 * @DESC:
 */
public class NormalClass {

    public BigDecimal div(Integer i,Integer j){
        return new BigDecimal(i).divide(new BigDecimal(j));
    }
}
