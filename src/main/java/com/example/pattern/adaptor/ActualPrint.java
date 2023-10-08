package com.example.pattern.adaptor;

public class ActualPrint extends AbstractPrint {

    private final Banner banner;

    public ActualPrint(String string) {
        this.banner = new Banner(string);
    }

    @Override
    public void printWeak() {
        banner.showWithParen();
    }

    @Override
    public void printStrong() {
        banner.showWithAster();
    }
}
