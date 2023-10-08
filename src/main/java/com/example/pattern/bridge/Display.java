package com.example.pattern.bridge;

public class Display {

    private final DisplayImpl impl;

    public Display(DisplayImpl impl) {
        this.impl = impl;
    }

    void open() {
        impl.rawOpen();
    }

    void print() {
        impl.rawPrint();
    }

    void close() {
        impl.rawClose();
    }

    public void display() {
        open();
        print();
        close();
    }
}
