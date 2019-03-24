package com.spring.bits.dao;

import com.spring.bits.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/11  15:28]
 * @DESC:
 */
@Repository("userMapper")
public interface UserMapper {


    User queryUserById(@Param("id") Integer id);

    void updateUserMoney(@Param("id")Integer id, @Param("money")BigDecimal money);
}
