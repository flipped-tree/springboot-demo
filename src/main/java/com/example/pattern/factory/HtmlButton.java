package com.example.pattern.factory;

/**
 * @author xingce
 * @date 2022/12/16 16:26
 */
public class HtmlButton implements Button{
    @Override
    public void render() {
        System.out.println("html button render");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("html button onclick");
    }
}
