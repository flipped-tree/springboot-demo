package com.example.pattern.builder;

public class TextBuilder extends Builder {
    private final StringBuffer buffer = new StringBuffer();           // 文档内容保存在该字段中

    public void makeTitle(String title) {                       // 纯文本的标题
        buffer.append("==============================\n");      // 装饰线
        buffer.append("『").append(title).append("』\n");                   // 为标题添加『』
        buffer.append("\n");                                    // 换行
    }

    public void makeString(String str) {                        // 纯文本的字符串
        buffer.append('■').append(str).append("\n");                       // 为字符串添加■
        buffer.append("\n");                                    // 换行
    }

    public void makeItems(String[] items) {                     // 纯文本的条目
        for (String item : items) {
            buffer.append("　・").append(item).append("\n");            // 为条目添加・
        }
        buffer.append("\n");                                    // 换行
    }

    public void close() {                                       // 完成文档
        buffer.append("==============================\n");      // 装饰线
    }

    public String getResult() {                                 // 完成的文档
        return buffer.toString();                               // 将StringBuffer变换为String
    }
}
