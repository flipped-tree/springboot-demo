package com.example.pattern.visitor;

public interface Element {
    void accept(Visitor v);
}
