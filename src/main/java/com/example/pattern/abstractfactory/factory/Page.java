package com.example.pattern.abstractfactory.factory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public abstract class Page {
    protected String title;
    protected String author;
    protected List<Item> content = new ArrayList<>();

    public Page(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void add(Item item) {
        content.add(item);
    }

    public void output() {
        String fileName = title + ".html";
        try (Writer writer = new FileWriter(fileName)) {
            writer.write(this.makeHTML());
            System.out.println(fileName + " succeed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract String makeHTML();
}
