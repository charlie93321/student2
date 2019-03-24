package com.spring.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/7  13:09]
 * @DESC:
 */
@Configuration
@ComponentScan(basePackages = {"com.spring.jdbc"})
@PropertySource(value={"classpath:c3p0.properties"})
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class DbConfiguration {


    @Bean(name="dataSource")
    public DataSource dataSource(C3p0DataSource c3p0DataSource) throws PropertyVetoException {
        ComboPooledDataSource dataSource= new ComboPooledDataSource();
        dataSource.setDriverClass(c3p0DataSource.getDriver());
        dataSource.setJdbcUrl(c3p0DataSource.getJdbcUrl());
        dataSource.setUser(c3p0DataSource.getUserName());
        dataSource.setPassword(c3p0DataSource.getPassword());
        dataSource.setAcquireIncrement(c3p0DataSource.getAcquireIncrement());
        dataSource.setMinPoolSize(c3p0DataSource.getMinPoolSize());
        dataSource.setMaxPoolSize(c3p0DataSource.getMaxPoolSize());
        dataSource.setInitialPoolSize(c3p0DataSource.getInitialPoolSize());
        dataSource.setMaxIdleTime(c3p0DataSource.getMaxIdleTime());
        dataSource.setMaxStatements(c3p0DataSource.getMaxStatements());
        dataSource.setNumHelperThreads(c3p0DataSource.getNumHelperThreads());
        dataSource.setAcquireRetryAttempts(c3p0DataSource.getAcquireRetryAttempts());
        dataSource.setAcquireRetryDelay(c3p0DataSource.getAcquireRetryDelay());
        dataSource.setCheckoutTimeout(c3p0DataSource.getCheckoutTimeout());
        dataSource.setIdleConnectionTestPeriod(c3p0DataSource.getIdleConnectionTestPeriod());
        dataSource.setAutomaticTestTable(c3p0DataSource.getAutomaticTestTable());
        dataSource.setTestConnectionOnCheckin(c3p0DataSource.isTestConnectionOnCheckin());
        dataSource.setUnreturnedConnectionTimeout(c3p0DataSource.getUnreturnedConnectionTimeout());
        dataSource.setDebugUnreturnedConnectionStackTraces(c3p0DataSource.isDebugUnreturnedConnectionStackTraces());
        return dataSource;
    }

    @Bean("jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource){


        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(@Qualifier("dataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
