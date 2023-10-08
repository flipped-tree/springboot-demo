package com.example.pattern.prototype;

import java.util.HashMap;

public class Manager {
    private final HashMap<String, Product> showCaseMap = new HashMap<>();

    void register(String prototypeName, Product product) {
        showCaseMap.put(prototypeName, product);
    }

    Product create(String prototypeName) {
        Product product = showCaseMap.get(prototypeName);
        return product.createClone();
    }
}
