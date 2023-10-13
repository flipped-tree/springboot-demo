package com.example.pattern.chainofresponsibility;

public class SpecialSupport extends Support {
    private final int number;                                 // 只能解决指定编号的问题

    public SpecialSupport(String name, int number) {    // 构造函数
        super(name);
        this.number = number;
    }

    protected boolean resolve(Trouble trouble) {        // 解决问题的方法
        return trouble.getNumber() == number;
    }
}
