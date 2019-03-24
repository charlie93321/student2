package com.spring.basic.domain;

import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/4  16:51]
 * @DESC:
 */


public class Person {
    /**
     *
     */
    @Value("${p1.id}")
    private Long id;
    @Value("${p1.userName}")
    private String userName;
    @Value("${p2.age}")
    private Integer age;

    public Person(Long id, String userName, Integer age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    public Person() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(userName, person.userName) &&
                Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, age);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Person{");
        sb.append("id=").append(id);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
