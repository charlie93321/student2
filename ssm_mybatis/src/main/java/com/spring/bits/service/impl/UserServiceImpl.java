package com.spring.bits.service.impl;

import com.spring.bits.dao.UserDao;
import com.spring.bits.dao.UserMapper;
import com.spring.bits.model.User;
import com.spring.bits.service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/11  15:31]
 * @DESC:
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

     @Setter
     private User user;


     @Autowired
     private UserMapper userMapper;

     @Autowired
     private UserDao userDao;

     @Override
     public User queryById(Integer id) {
          //User user2=userMapper.queryUserById(id);
          User user2=userDao.selectByPrimaryKey(id);
          this.user=user2;
          return user2;
     }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public boolean changeMoney(Integer id1, Integer id2, BigDecimal money) {


        userMapper.updateUserMoney(id1, money.multiply(new BigDecimal(-1)));
        System.out.println("账户:" + id1 + "转出$" + money.doubleValue());
        int i=1/0;
        userMapper.updateUserMoney(id2, money);
        System.out.println("账户:" + id2 + "转入$" + money.doubleValue());

        return true;
    }
}
