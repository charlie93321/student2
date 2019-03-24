package com.spring.basic;

import com.spring.basic.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/4  16:49]
 * @DESC: 主配置类
 */

@ComponentScan(basePackages = {"com.spring.basic"}) /*指定扫描包的范围*/
@Configuration
@PropertySource(name="basic-property",
        value={"classpath:a1.properties","classpath:a2.properties"},encoding = "utf-8")
public class MainConfig {

    @Bean
    public Person getPerson(){

        return new Person();
    }


}
