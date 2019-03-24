package com.spring.jdbc;

import lombok.Data;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/7  15:09]
 * @DESC:
 */
@Data
public class User {
    private Integer id;
    private String name;
    private Double money;

    public User() {
    }

    public User(Integer id, String name, Double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }
}
