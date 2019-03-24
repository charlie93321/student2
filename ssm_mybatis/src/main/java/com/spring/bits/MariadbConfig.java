package com.spring.bits;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/11  15:21]
 * @DESC:
 */
@Component
/*配置扫描器*/
@MapperScan(basePackages = {"com.spring.bits.dao"},
        sqlSessionTemplateRef = "sqlSessionTemplate")
public class MariadbConfig {

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


    /** 整合 mybatis-spring 整合 */
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean= new SqlSessionFactoryBean();

        bean.setDataSource(dataSource);



        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapper/hadoopdb/*.xml"));

        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    /** 事物 */
    @Bean("transactionManager")
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }


}
