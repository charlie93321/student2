package com.spring.bits;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.spring.bits.dao.UserDao;
import com.spring.bits.dao.UserMapper;
import com.spring.bits.service.UserService;
import com.spring.bits.service.impl.UserServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/7  13:09]
 * @DESC:
 */
//@Configuration/**配置类*/
@ComponentScan(
        basePackages = {"com.spring.bits"}
        ,excludeFilters = {
                @ComponentScan.Filter(type=FilterType.ANNOTATION,classes = {Controller.class})
}
) /**两层层扫描*/
@PropertySource(value={"classpath:c3p0.properties"})  /** 配合value 读取配置文件*/
@EnableAspectJAutoProxy/**aop 切面 开启 */
@EnableTransactionManagement/**开启事物*/
public class AppConfiguration {


   /* @Bean("userService")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserService userService(UserDao userDao,UserMapper userMapper){
        UserServiceImpl userService=new UserServiceImpl();
        userService.setUserDao(userDao);
        userService.setUserMapper(userMapper);
        return userService;
    }*/


}
