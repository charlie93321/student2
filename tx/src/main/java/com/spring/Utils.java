package com.spring;

import com.spring.jdbc.C3p0DataSource;

import java.lang.reflect.Field;

import static org.springframework.cglib.core.TypeUtils.upperFirst;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/7  14:31]
 * @DESC:
 */
public class Utils {

      public static void main(String[] args){

          Class clazz=C3p0DataSource.class;
          String prefix="dataSource";

          Field[] fields=clazz.getDeclaredFields();

          for (Field field : fields) {
                 System.out.println(prefix+".set"+upperFirst(field.getName())+"();");
          }

      }
}
