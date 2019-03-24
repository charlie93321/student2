package com.spring.auto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  16:37]
 * @DESC:
 */
@Component
@Data
public class Room {

    //@Qualifier("st01")
    @Autowired
    private Student student;


    @Resource(name="st02")
    private Student student2;

    @Inject
    private Student student3;

    @Resource(name="stDev")
    private Student student4;
}
