package com.example.lambda;

/**
 * @author xingce
 * @date 2021/12/29 13:20
 */
public class Lambda {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private Lambda(String name){
        this.name = name;
    }

    public static Lambda of(String name){
        return new Lambda(name);
    }
}
