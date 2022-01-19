package com.example.queue;

/**
 * @author xingce
 * @date 2022/1/17 20:00
 */
public class Bread {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void eat() {
        System.out.println("eat bread");
    }
}
