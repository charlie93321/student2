package com.spring.bits.dao;

import com.spring.bits.model.User;
import com.spring.bits.model.UserExample;
import org.springframework.stereotype.Repository;

/**
 * TestDAO继承基类
 */
@Repository("userDao")
public interface UserDao extends MyBatisBaseDao<User, Integer, UserExample> {



}