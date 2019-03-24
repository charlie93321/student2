package com.spring.beanLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  10:22]
 * @DESC:
 */
@Component
@Lazy
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClassRoom {
    @Autowired
    private  Person person08;


    @Autowired
    private  Person person09;

    @Qualifier("person12")
    @Autowired
    private  Person person07;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ClassRoom{");
        sb.append("person08=").append(person08);
        sb.append(", person09=").append(person09);
        sb.append(", person07=").append(person07);
        sb.append('}');
        return sb.toString();
    }
}
