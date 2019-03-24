package com.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  18:25]
 * @DESC:
 */
@Aspect
public class AopClass {


    @Pointcut(value="execution (public * com.spring.aop.NormalClass.*(..))")
    public void pointcut(){}

    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint){
          System.out.println(mname(joinPoint)+"方法开始执行,参数是:"+margs(joinPoint));
    }

    private String margs(JoinPoint joinPoint) {
        Object[] args=joinPoint.getArgs();
        if(null==args || args.length==0) return "";
        return Arrays.asList(args).toString();
    }

    @After("pointcut()")
    public void after_end(JoinPoint jointPoint){
         System.out.println(mname(jointPoint)+"方法执行成功,并结束");
    }

    @AfterReturning(value = "pointcut()",returning = "result")
    public void after_return(JoinPoint joinPoint,Object result){
          System.out.println(mname(joinPoint)+"方法执行成功并返回:"+result);
    }

    @AfterThrowing(value = "pointcut()",throwing = "exception")
    public void after_exception(JoinPoint joinPoint,Exception exception){
          System.out.println(mname(joinPoint)+"方法执行失败,抛出异常:"+exception.getMessage());
    }

    public String mname(JoinPoint joinPoint){
        return joinPoint.getSignature().getName();
    }


    
}
