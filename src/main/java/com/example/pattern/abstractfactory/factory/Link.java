package com.example.pattern.abstractfactory.factory;

public abstract class Link extends Item {
    protected String url;

    public Link(String captain, String url) {
        super(captain);
        this.url = url;
    }
}
