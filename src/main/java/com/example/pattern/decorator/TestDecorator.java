package com.example.pattern.decorator;

public class TestDecorator {
    public static void main(String[] args) {
        Display h1 = new StringDisplay("hello world");
        Display h2 = new SideBorder(h1, '#');
        Display h3 = new FullBorder(h2);
        h1.show();
        h2.show();
        h3.show();

        Display h4 = new SideBorder(new FullBorder(new FullBorder(new SideBorder(new FullBorder(new StringDisplay(
                "你好,世界")), '*'))), '/');
        h4.show();
    }
}
