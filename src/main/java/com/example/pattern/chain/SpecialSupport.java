package com.example.pattern.chain;

public class SpecialSupport extends Support {
    private final int number;

    public SpecialSupport(String name, int number) {
        super(name);
        this.number = number;
    }

    @Override
    boolean resolve(Trouble trouble) {
        return trouble.getNumber() == number;
    }
}
