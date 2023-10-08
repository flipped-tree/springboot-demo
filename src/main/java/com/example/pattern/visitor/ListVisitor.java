package com.example.pattern.visitor;

import java.util.Iterator;

public class ListVisitor extends Visitor {

    private String currentDir = "";

    @Override
    void visit(File file) {
        System.out.println(currentDir + "/" + file);
    }

    @Override
    void visit(Directory directory) {
        System.out.println(currentDir + "/" + directory);
        String saveDir = currentDir;
        currentDir = currentDir + "/" + directory.getName();
        Iterator<Entry> iterator = directory.iterator();
        while (iterator.hasNext()) {
            Entry next = iterator.next();
            next.accept(this);
        }
        currentDir = saveDir;
    }
}
