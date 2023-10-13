package com.example.pattern.abstractfactory.listfactory;


import com.example.pattern.abstractfactory.factory.Factory;
import com.example.pattern.abstractfactory.factory.Link;
import com.example.pattern.abstractfactory.factory.Page;
import com.example.pattern.abstractfactory.factory.Tray;

public class ListFactory extends Factory {
    public Link createLink(String caption, String url) {
        return new ListLink(caption, url);
    }

    public Tray createTray(String caption) {
        return new ListTray(caption);
    }

    public Page createPage(String title, String author) {
        return new ListPage(title, author);
    }
}
