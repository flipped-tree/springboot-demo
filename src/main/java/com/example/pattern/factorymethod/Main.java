package com.example.pattern.factorymethod;

import com.example.pattern.factorymethod.framework.Factory;
import com.example.pattern.factorymethod.framework.Product;
import com.example.pattern.factorymethod.idcard.IDCardFactory;

public class Main {
    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product card1 = factory.create("小明");
        Product card2 = factory.create("小红");
        Product card3 = factory.create("小刚");
        card1.use();
        card2.use();
        card3.use();
    }
}
