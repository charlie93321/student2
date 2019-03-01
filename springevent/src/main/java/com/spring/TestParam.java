package com.spring;

import java.util.Objects;


public class TestParam {
    private String email;

    public String getEmail() {
        return email;
    }

    public TestParam setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestParam testParam = (TestParam) o;
        return Objects.equals(email, testParam.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email);
    }
}