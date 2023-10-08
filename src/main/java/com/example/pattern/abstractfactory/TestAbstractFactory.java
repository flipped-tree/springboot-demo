package com.example.pattern.abstractfactory;

import com.example.pattern.abstractfactory.factory.Factory;
import com.example.pattern.abstractfactory.factory.Link;
import com.example.pattern.abstractfactory.factory.Page;
import com.example.pattern.abstractfactory.factory.Tray;

public class TestAbstractFactory {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main class");
            System.out.println("Example 1:java Main listFactory");
            System.out.println("Example 2:java main tableFactory");
            System.exit(0);
        }
        Factory factory = Factory.getFactory(args[0]);

        Link people = factory.createLink("people", "www.people.com");
        Link bright = factory.createLink("bright", "www.bright.com");

        Tray trayNews = factory.createTray("日报");
        trayNews.add(people);
        trayNews.add(bright);

        Page page = factory.createPage("LinkPage", "jack");
        page.add(trayNews);
        page.output();
    }
}
