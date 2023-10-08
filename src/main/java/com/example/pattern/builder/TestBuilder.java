package com.example.pattern.builder;

public class TestBuilder {
    public static void main(String[] args) {
        Builder builder = new Builder.PatternBuilder().setText("test").build();
        System.out.println(builder.getText());
    }
}
