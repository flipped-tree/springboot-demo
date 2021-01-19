package com.example.springboot.chain;

public interface BaseService<Child> extends Ordered {

    Child setChild(Child child);

}
