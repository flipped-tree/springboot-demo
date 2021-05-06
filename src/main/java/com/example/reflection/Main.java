package com.example.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author cexing
 * @date 2021/4/30 15:44
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> targetClass = Class.forName("com.example.reflection.Person");
        Person person = (Person) targetClass.newInstance();
        // 获取所有类中所有定义的方法
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print(method.getName());
        }

        System.out.println();

        // 获取指定方法并调用
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod",
                String.class);

        publicMethod.invoke(person, " Java");
        // 获取指定参数并对参数进行修改
        Field field = targetClass.getDeclaredField("name");
        //为了对类中的参数进行修改我们取消安全检查
        field.setAccessible(true);
        field.set(person, " python");
        // 调用 private 方法
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        // 为了调用private方法我们取消安全检查
        privateMethod.setAccessible(true);
        privateMethod.invoke(person);
    }
}
