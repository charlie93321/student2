package com.spring.auto;

import lombok.Data;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  16:33]
 * @DESC:
 */
@Data
public class Student {
    private Long sid;
    private String name;
    private String grade;
    private String country="中国";

    public Student() {
    }

    public Student(Long sid, String name, String grade, String country) {
        this.sid = sid;
        this.name = name;
        this.grade = grade;
        this.country = country;
    }
}
