package com.spring.bits.service;

import com.spring.bits.model.User;

import java.math.BigDecimal;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/11  15:30]
 * @DESC:
 */
public interface UserService {


    User queryById(Integer id);

    public User getUser();

    boolean changeMoney(Integer id1, Integer id2, BigDecimal money);
}
