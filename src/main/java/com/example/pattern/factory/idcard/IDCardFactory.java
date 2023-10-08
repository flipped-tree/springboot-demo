package com.example.pattern.factory.idcard;

import com.example.pattern.factory.framework.Factory;
import com.example.pattern.factory.framework.Product;

import java.util.ArrayList;
import java.util.List;

public class IDCardFactory extends Factory {
    private final List<String> owners = new ArrayList<>();

    @Override
    public Product createProduct(String owner) {
        return new IDCard(owner);
    }

    @Override
    public void registerProduct(Product product) {
        owners.add(((IDCard) product).getOwner());
    }
}
