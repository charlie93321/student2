package com.spring;

import java.util.Objects;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/1  15:04]
 * @DESC:
 */
public class Result {
    private Integer count;
    private String taskName;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(count, result.count) &&
                Objects.equals(taskName, result.taskName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(count, taskName);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Result{");
        sb.append("count=").append(count);
        sb.append(", taskName='").append(taskName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
