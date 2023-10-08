package com.example.pattern.adaptor;

public class TestAdaptor {
    public static void main(String[] args) {
        Print p = new PrintBanner("Hello");
        p.printStrong();
        p.printWeak();

        AbstractPrint print = new ActualPrint("Hello");
        print.printStrong();
        print.printWeak();
    }
}
