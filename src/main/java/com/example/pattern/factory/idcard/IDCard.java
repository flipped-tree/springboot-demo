package com.example.pattern.factory.idcard;

import com.example.pattern.factory.framework.Product;

public class IDCard extends Product {
    String owner;

    IDCard(String owner) {
        System.out.println("produce " + owner + " ‘s idCard");
        this.owner = owner;
    }

    @Override
    public void use() {
        System.out.println("use " + owner + " ’s idCard");
    }

    public String getOwner() {
        return owner;
    }
}
