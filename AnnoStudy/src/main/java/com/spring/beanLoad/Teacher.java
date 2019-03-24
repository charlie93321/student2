package com.spring.beanLoad;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  11:09]
 * @DESC:
 */
public class Teacher {
    @Autowired
    private Person bill;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Teacher{");
        sb.append("bill=").append(bill);
        sb.append('}');
        return sb.toString();
    }
}
