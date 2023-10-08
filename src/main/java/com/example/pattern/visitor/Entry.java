package com.example.pattern.visitor;

import java.util.Iterator;

public abstract class Entry implements Element {

    public abstract String getName();

    public abstract int getSize();

    public Entry add(Entry entry) {
        throw new RuntimeException();
    }

    public Iterator<Entry> iterator() {
        throw new RuntimeException();
    }

    public String toString() {
        return getName() + "(" + getSize() + ")";
    }
}
