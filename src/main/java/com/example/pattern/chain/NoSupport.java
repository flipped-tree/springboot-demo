package com.example.pattern.chain;

public class NoSupport extends Support {
    public NoSupport(String name) {
        super(name);
    }

    @Override
    boolean resolve(Trouble trouble) {
        return false;
    }
}
