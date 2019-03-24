package com.servlet.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/10  15:36]
 * @DESC:
 */
@ComponentScan(basePackages = "com.servlet.mvc" , excludeFilters = {
        @ComponentScan.Filter(type=FilterType.ANNOTATION,value={org.springframework.stereotype.Controller.class})
})
public class RootConfiguration {



}
