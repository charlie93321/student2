package com.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/4  17:44]
 * @DESC:
 */
public class UnSafeUtil {

    public static long location(Object object) throws Throwable {

        Unsafe unsafe = getUnsafe();



        Object[] array = new Object[] {object};



        long baseOffset = unsafe.arrayBaseOffset(Object[].class);

        int addressSize = unsafe.addressSize();

        long location;

        switch (addressSize) {

            case 4:

                location = unsafe.getInt(array, baseOffset);

                break;

            case 8:

                location = unsafe.getLong(array, baseOffset);

                break;

            default:

                throw new Error("unsupported address size: " + addressSize);

        }

        return (location);

    }

    private static  Unsafe getUnsafe() throws Throwable {

        Class<?> unsafeClass = Unsafe.class;

        for (Field f : unsafeClass.getDeclaredFields()) {

            if ("theUnsafe".equals(f.getName())) {

                f.setAccessible(true);

                return (Unsafe) f.get(null);

            }

        }

        throw new IllegalAccessException("no declared field: theUnsafe");

    }

    public static void printlnLocation(Class clazz, AnnotationConfigApplicationContext app) throws Throwable {
        Map<String, Object> map=app.getBeansOfType(clazz);
        if(null==map || map.isEmpty()) return;
        for (String beanName: map.keySet()) {
            Object bean=map.get(beanName);
            long location=UnSafeUtil.location(bean);
            System.out.println(beanName+" 's location is :0x"+Long.toHexString(location).toUpperCase());
        }


    }
}
