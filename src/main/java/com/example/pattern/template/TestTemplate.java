package com.example.pattern.template;

public class TestTemplate {
    public static void main(String[] args) {
        AbstractDisplay charDisplay = new CharDisplay('H');
        AbstractDisplay stringDisplay = new StringDisplay("Hello");
        charDisplay.display();
        stringDisplay.display();
    }
}
