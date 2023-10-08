package com.example.pattern.factory;

import com.example.pattern.factory.framework.Factory;
import com.example.pattern.factory.framework.Product;
import com.example.pattern.factory.idcard.IDCardFactory;

public class TestFactory {
    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product jackIdCard = factory.create("Jack");
        Product roseIdCard = factory.create("Rose");
        jackIdCard.use();
        roseIdCard.use();
    }
}
