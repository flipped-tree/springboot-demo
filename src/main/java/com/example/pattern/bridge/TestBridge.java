package com.example.pattern.bridge;

public class TestBridge {
    public static void main(String[] args) {
        Display d1 = new Display(new StringDisplayImpl("hello china"));
        Display d2 = new Display(new StringDisplayImpl("hello world"));
        CountryDisplay d3 = new CountryDisplay(new StringDisplayImpl("hello universe"));

        d1.display();
        d2.display();
        d3.display();
        d3.multiDisplay(5);
    }
}
