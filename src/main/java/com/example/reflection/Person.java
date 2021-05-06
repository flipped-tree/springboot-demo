package com.example.reflection;

/**
 * @author cexing
 * @date 2021/4/30 15:42
 */
public class Person {
    private final String name;

    public Person() {
        name = "person";
    }

    public void publicMethod(String message) {
        System.out.println("i love" + message);
    }

    private void privateMethod() {
        System.out.println("private method is" + name);
    }
}
