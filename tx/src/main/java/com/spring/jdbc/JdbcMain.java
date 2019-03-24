package com.spring.jdbc;

import com.spring.jdbc.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;



/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/7  16:23]
 * @DESC:
 */
public class JdbcMain {


      public static void main(String[] args){
          AnnotationConfigApplicationContext app=null;
          try {
              app= new AnnotationConfigApplicationContext(DbConfiguration.class);

              UserService userService = app.getBean(UserService.class);

              //userService.putMoney(new User(1,"小凡",100.98d));
              //userService.putMoney(new User(2,"琳儿",200.98d));
              User user1 = new User(1, "小凡", 100.98d);
              User user2 = new User(2, "琳儿", 200.98d);
              JdbcTemplate jdbcTemplate=app.getBean(JdbcTemplate.class);
              userService.changeMoney(user1, user2, 50.00);



          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              if(null!=app)
                     app.close();
          }

      }
}
