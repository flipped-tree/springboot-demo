package com.example.pattern.abstractfactory.listfactory;

import com.example.pattern.abstractfactory.factory.Item;
import com.example.pattern.abstractfactory.factory.Page;

public class ListPage extends Page {
    public ListPage(String title, String author) {
        super(title, author);
    }

    public String makeHTML() {
        StringBuilder builder = new StringBuilder();
        builder.append("<html><head><title>").append(title).append("</title></head>\n");
        builder.append("<body>\n");
        builder.append("<h1>").append(title).append("</h1>\n");
        builder.append("<ul>\n");
        for (Item item : content) {
            builder.append(item.makeHTML());
        }
        builder.append("</ul>\n");
        builder.append("<hr><address>").append(author).append("</address>");
        builder.append("</body></html>\n");
        return builder.toString();
    }
}
