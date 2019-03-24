package com.spring.jdbc.impl;

import com.spring.jdbc.User;
import com.spring.jdbc.service.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/7  15:11]
 * @DESC:
 */
@Repository
public class UserDaoImpl implements UserDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void changeMoney(User offer, double v) {

        jdbcTemplate.update("update hadoopdb.test t" +
                " set  t.money=t.money+? where t.id=?",v,offer.getId());

    }

    @Override
    public void insertUser(User user) {
        jdbcTemplate.update("insert into hadoopdb.test values(?,?,?)"
                ,user.getId(),user.getName(),user.getMoney());
    }

    @Override
    public void changeMoney(User offer, double v, Connection connection) throws Exception {

        if(null==connection)throw new Exception("数据库无效连接");
        PreparedStatement ps=connection.prepareStatement("update hadoopdb.test t" +
                " set  t.money=t.money+? where t.id=?");

        ps.setDouble(1,v);
        ps.setInt(2,offer.getId());

        ps.execute();



    }
}
