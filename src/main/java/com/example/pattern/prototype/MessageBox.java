package com.example.pattern.prototype;

public class MessageBox implements Product {
    private final char decoChar;

    public MessageBox(char decoChar) {
        this.decoChar = decoChar;
    }

    @Override
    public void use(String string) {
        int length = string.getBytes().length;
        for (int i = 0; i < length + 4; i++) {
            System.out.print(decoChar);
        }
        System.out.println();
        System.out.println(decoChar + " " + string + " " + decoChar);
        for (int i = 0; i < length + 4; i++) {
            System.out.print(decoChar);
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
