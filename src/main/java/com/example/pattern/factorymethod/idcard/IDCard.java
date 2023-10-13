package com.example.pattern.factorymethod.idcard;

import com.example.pattern.factorymethod.framework.Product;

public class IDCard extends Product {
    private final String owner;

    IDCard(String owner) {
        System.out.println("制作" + owner + "的ID卡。");
        this.owner = owner;
    }

    public void use() {
        System.out.println("使用" + owner + "的ID卡。");
    }

    public String getOwner() {
        return owner;
    }
}
