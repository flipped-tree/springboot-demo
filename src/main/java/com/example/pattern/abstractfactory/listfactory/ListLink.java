package com.example.pattern.abstractfactory.listfactory;

import com.example.pattern.abstractfactory.factory.Link;

public class ListLink extends Link {
    public ListLink(String captain, String url) {
        super(captain, url);
    }

    @Override
    public String makeHTML() {
        return "listLink";
    }
}
