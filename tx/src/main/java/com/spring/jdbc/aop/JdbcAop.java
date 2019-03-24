package com.spring.jdbc.aop;

import com.spring.jdbc.impl.UserServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/7  16:36]
 * @DESC:
 */
@Component
@Aspect
public class JdbcAop {

    @Pointcut(value="execution(public * com.spring.jdbc.impl.UserServiceImpl.changeMoney2(..))")
    public void pointCut(){};

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Around(value="pointCut()")
    public Object  aroundAtomic(ProceedingJoinPoint joinPoint) throws SQLException {
        Object[] args=joinPoint.getArgs();
        boolean atomic=false;
        Connection connection=null;
        if(null!=args && args.length>0){
            for (Object arg : args) {
                  if(arg instanceof Connection){
                      atomic=true;
                      connection= (Connection) arg;
                      break;
                  }
            }
        }
        if(atomic)
             return atomicMethodInvoke(joinPoint,args,connection);
        else return simpleInvoke(joinPoint,args);
    }

    private Object simpleInvoke(ProceedingJoinPoint joinPoint, Object[] args) {
        Object object=null;
        try {
            object=joinPoint.proceed(args);
            return object;
        } catch (Throwable throwable) {
            throwable.printStackTrace();

        }
        return object;
    }

    private Object atomicMethodInvoke(ProceedingJoinPoint joinPoint,Object[] args,Connection connection) {

        Object object=null;
        try {
            connection.setAutoCommit(false);

            object=joinPoint.proceed(args);
            connection.commit();
            return object;
        } catch (Throwable throwable) {
            try {
                connection.rollback();
                System.out.println("回滚成功"+throwable.getMessage());
            } catch (SQLException e) {
                System.out.println("回滚失败");
            }
        }finally {
            try {
                if(null!=connection)
                    connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return object;
    }

}
