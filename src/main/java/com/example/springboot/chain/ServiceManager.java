package com.example.springboot.chain;

public interface ServiceManager<Child> {

    void addChild(Child child);

}
