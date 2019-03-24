package com.spring.jdbc.impl;

import com.spring.jdbc.User;
import com.spring.jdbc.service.UserDao;
import com.spring.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/7  15:11]
 * @DESC:
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void changeMoney(User offer, User receive, Double money) {
         userDao.changeMoney(offer,money*(-1));
         System.out.println(offer.getName()+"转出金钱:$"+money);
         int i=10/0;
         userDao.changeMoney(receive,money);
         System.out.println(receive.getName()+"收到金钱:$"+money);
    }

    @Override
    public void changeMoney2(User offer, User receive, Double money, Connection connection) throws Exception {
        userDao.changeMoney(offer,money*(-1),connection);
        System.out.println(offer.getName()+"转出金钱:$"+money);
        int i=10/0;
        userDao.changeMoney(receive,money,connection);
        System.out.println(receive.getName()+"收到金钱:$"+money);
    }

    @Override
    public void putMoney(User user) {
        userDao.insertUser(user);
        System.out.println(user.getName()+"往账户中存入金钱:$"+user.getMoney());
    }
}
