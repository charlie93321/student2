package com.spring.bits.controller;

import com.spring.bits.dao.UserDao;
import com.spring.bits.model.Result;
import com.spring.bits.model.User;
import com.spring.bits.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/11  15:30]
 * @DESC:
 */
@Controller
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserController  implements BeanFactoryAware {

    @Autowired
    private UserService userService;

    private BeanFactory beanFactory;

    @RequestMapping("/user/{id}")
    public @ResponseBody User queryUser(@PathVariable  Integer id){

        //userService= (UserService) beanFactory.getBean("userService");

        System.out.println("userService's user is:"+userService.getUser()+",addr==>"+userService.hashCode());
        User user=userService.queryById(id);



        return user;
    }



    @RequestMapping("/user/{id1}/{id2}/{money}")
    public @ResponseBody
    Result<String> changeMoney(@PathVariable  Integer id1,
                       @PathVariable Integer id2,
                       @PathVariable BigDecimal money){

        ///userService= (UserService) beanFactory.getBean("userService");

        System.out.println("userService's user is:"+userService.getUser()+",addr==>"+userService.hashCode());

        boolean success =false;
        try {
             success = userService.changeMoney(id1, id2, money);
        }catch (Exception e){
              System.out.println("发生异常==>"+e.getMessage());
            success=false;
        }finally {
            if(success) return Result.ok("转账成功");
            else return Result.error("转账失败");
        }
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory=beanFactory;
    }
}
