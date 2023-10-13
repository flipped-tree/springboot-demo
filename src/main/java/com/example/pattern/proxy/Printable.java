package com.example.pattern.proxy;

public interface Printable {
    void setPrinterName(String name);   // 设置名字

    String getPrinterName();            // 获取名字

    void print(String string);          // 显示文字（打印输出）
}
