package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/1  13:55]
 * @DESC:
 */
@ComponentScan(basePackages = {"com.spring"})
@Configuration/*指定这是一个替代配置文件的配置类*/
@EnableAsync/*开启异步支持*/
public class MainConfig {
    @Bean(name="mainExecutor")
    public ThreadPoolTaskExecutor getMainExecutor(){
        ThreadPoolTaskExecutor executor= new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(6);
        executor.initialize();
        return executor;
    }


    @Bean(name="logExecutor")
    public TaskExecutor getLogExecutor(){
        ThreadPoolTaskExecutor executor= new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setMaxPoolSize(7);
        executor.setQueueCapacity(10);
        executor.initialize();
        return executor;
    }
}
