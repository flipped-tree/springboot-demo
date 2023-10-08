package com.example.pattern.abstractfactory.factory;

public abstract class Item {
    protected String captain;

    public Item(String captain) {
        this.captain = captain;
    }

    public abstract String makeHTML();
}
