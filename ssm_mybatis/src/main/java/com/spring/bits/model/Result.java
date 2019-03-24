package com.spring.bits.model;

import lombok.Data;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/11  19:40]
 * @DESC:
 */
@Data
public class Result<T> {

    private T data;

    private String message;

    private String code;

    public static<T>  Result<T>  ok(T t){
        Result<T> result=new Result<>();
        result.setCode("10000");
        result.setMessage("请求成功");
        result.setData(t);
        return  result;
    }


    public static<T>  Result<T>  error(T t){
        Result<T> result=new Result<>();
        result.setCode("00001");
        result.setMessage("请求失败");
        result.setData(t);
        return  result;
    }
}
