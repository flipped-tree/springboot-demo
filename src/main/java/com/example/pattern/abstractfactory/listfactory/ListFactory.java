package com.example.pattern.abstractfactory.listfactory;

import com.example.pattern.abstractfactory.factory.Factory;
import com.example.pattern.abstractfactory.factory.Link;
import com.example.pattern.abstractfactory.factory.Page;
import com.example.pattern.abstractfactory.factory.Tray;

public class ListFactory extends Factory {
    @Override
    public Link createLink(String captain, String url) {
        return new ListLink(captain, url);
    }

    @Override
    public Tray createTray(String captain) {
        return new ListTray(captain);
    }

    @Override
    public Page createPage(String title, String author) {
        return new ListPage(title, author);
    }
}
