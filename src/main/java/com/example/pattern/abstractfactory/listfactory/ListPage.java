package com.example.pattern.abstractfactory.listfactory;

import com.example.pattern.abstractfactory.factory.Page;

public class ListPage extends Page {
    public ListPage(String title, String author) {
        super(title, author);
    }

    @Override
    protected String makeHTML() {
        return "listPage";
    }
}
