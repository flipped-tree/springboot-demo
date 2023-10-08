package com.example.pattern.visitor;

public class TestVisitor {
    public static void main(String[] args) {
        System.out.println("Making root entries ...");
        Directory rootDir = new Directory("root");
        Directory binDir = new Directory("bin");
        Directory tmpDir = new Directory("tmp");
        Directory usrDir = new Directory("usr");
        rootDir.add(binDir);
        rootDir.add(tmpDir);
        rootDir.add(usrDir);
        binDir.add(new File("vi", 1000));
        binDir.add(new File("latex", 2000));
        rootDir.accept(new ListVisitor());
    }
}
