package com.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Hello world!
 *
 */
public class AppTest
{


     public static void main(String[] args) throws ExecutionException, InterruptedException {

       // testXml();
        // testAnno();

         testAnnoBack();
     }

    public static void testXml()
    {
        ClassPathXmlApplicationContext app=new ClassPathXmlApplicationContext("classpath:spring-event.xml");

        Test test=app.getBean(Test.class);

        for (int i = 0; i <10 ; i++) {
            test.testAsyncMethod("任务"+i);
        }

        System.out.println("我已经执行了！");


    }


    public static  void testAnno()
    {
        AnnotationConfigApplicationContext app=
                new AnnotationConfigApplicationContext(MainConfig.class);

        Test test=app.getBean(Test.class);

        for (int i = 0; i <10 ; i++) {
            test.testAsyncMethod("任务"+i);
        }
        System.out.println("我已经执行了！");


    }

    public static  void testAnnoBack() throws InterruptedException, ExecutionException {
        AnnotationConfigApplicationContext app=
                new AnnotationConfigApplicationContext(MainConfig.class);

        AsynService test=app.getBean(AsynService.class);
        List<Future<Result>> rs=new ArrayList<Future<Result>>();
        for (int i = 0; i <10 ; i++) {
            rs.add(test.getResult("任务"+i));
        }
        long now=System.currentTimeMillis();
        System.out.println("我已经执行了！"+now);
        int sum=0;
        for (Future<Result> r : rs) {
            long nowTemp=System.currentTimeMillis();
              Result temp=null;
               if(null!=r){
                   temp=r.get();
                   sum+=temp.getCount();
               }
            System.out.println("main获取值完毕:"+temp+",耗时:"
                    +(System.currentTimeMillis()-nowTemp)/1000);
        }
        System.out.println("最终结果为:"+sum+",耗时:"+(System.currentTimeMillis()-now)/1000);
    }
}
