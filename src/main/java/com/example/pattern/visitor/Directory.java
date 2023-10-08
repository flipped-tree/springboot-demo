package com.example.pattern.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Directory extends Entry {

    private final String name;
    private final List<Entry> directoryList = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int size = 0;
        for (Entry next : directoryList) {
            size += next.getSize();
        }
        return size;
    }

    public Entry add(Entry entry) {
        directoryList.add(entry);
        return this;
    }

    public Iterator<Entry> iterator() {
        return directoryList.iterator();
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
