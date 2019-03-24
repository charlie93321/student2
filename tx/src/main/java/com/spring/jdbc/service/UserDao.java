package com.spring.jdbc.service;

import com.spring.jdbc.User;

import java.sql.Connection;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/7  15:11]
 * @DESC:
 */
public interface UserDao {
    void changeMoney(User offer, double v);

    void insertUser(User user);

    void changeMoney(User offer, double v, Connection connection) throws Exception;
}
