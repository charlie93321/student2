package com.asyn.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/11  12:18]
 * @DESC:
 */
@ComponentScan(basePackages = {"com.asyn.mvc"},excludeFilters = {
        @ComponentScan.Filter(type=FilterType.ANNOTATION,classes = {Controller.class})
})
public class RootConfiguration {

}
