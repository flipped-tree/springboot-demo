package com.example.pattern.factorymethod.idcard;

import com.example.pattern.factorymethod.framework.Factory;
import com.example.pattern.factorymethod.framework.Product;

import java.util.ArrayList;
import java.util.List;

public class IDCardFactory extends Factory {
    private final List owners = new ArrayList();

    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }

    protected void registerProduct(Product product) {
        owners.add(((IDCard) product).getOwner());
    }

    public List getOwners() {
        return owners;
    }
}
