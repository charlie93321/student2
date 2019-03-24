package com.spring.jdbc.service;

import com.spring.jdbc.User;

import java.sql.Connection;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/7  15:10]
 * @DESC:
 */
public interface UserService {

    public void  changeMoney(User offer,User receive,Double money);
    public void  changeMoney2(User offer,User receive,Double money,Connection connection) throws Exception;
    public void  putMoney(User user);
}
