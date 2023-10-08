package com.example.pattern.prototype;

public interface Product extends Cloneable {
    void use(String string);

    Product createClone();
}
