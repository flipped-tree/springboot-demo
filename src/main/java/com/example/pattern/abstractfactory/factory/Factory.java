package com.example.pattern.abstractfactory.factory;

public abstract class Factory {
    public static Factory getFactory(String className) {
        Factory factory;
        try {
            factory = (Factory) Class.forName(className).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return factory;
    }

    public abstract Link createLink(String captain, String url);

    public abstract Tray createTray(String captain);

    public abstract Page createPage(String title, String author);
}
