package com.example.pattern.factory;

/**
 * @author xingce
 * @date 2022/12/16 16:27
 */
public class WindowsButton implements Button{
    @Override
    public void render() {
        System.out.println("windows button render");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("windows button onclick");
    }
}
