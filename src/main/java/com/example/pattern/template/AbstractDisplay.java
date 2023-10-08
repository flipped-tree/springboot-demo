package com.example.pattern.template;

public abstract class AbstractDisplay {
    abstract void open();

    abstract void print();

    abstract void close();

    public void display() {
        open();
        for (int i = 0; i < 5; i++) {
            print();
        }
        close();
    }
}
