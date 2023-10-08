package com.example.pattern.prototype;

public class TestPrototype {
    public static void main(String[] args) {
        Manager manager = new Manager();
        UnderlinePen ulPen = new UnderlinePen('~');
        MessageBox mBox = new MessageBox('*');
        MessageBox aBox = new MessageBox('/');
        manager.register("strong message", ulPen);
        manager.register("warning box", mBox);
        manager.register("slash box", aBox);

        Product p1 = manager.create("strong message");
        p1.use("Hello world");
        Product p2 = manager.create("warning box");
        p2.use("Hello world");
        Product p3 = manager.create("slash box");
        p3.use("Hello world");
    }
}
