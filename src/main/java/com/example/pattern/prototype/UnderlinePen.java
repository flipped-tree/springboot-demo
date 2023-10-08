package com.example.pattern.prototype;

public class UnderlinePen implements Product {
    private final char ulChar;

    public UnderlinePen(char ulChar) {
        this.ulChar = ulChar;
    }

    @Override
    public void use(String string) {
        int length = string.getBytes().length;
        System.out.println("\"" + string + "\"");
        System.out.print(" ");
        for (int i = 0; i < length; i++) {
            System.out.print(ulChar);
        }
        System.out.println();
    }

    @Override
    public Product createClone() {
        Product product;
        try {
            product = (Product) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
}
