package com.example.pattern.iterator;

public class TestIterator {
    public static void main(String[] args) {
        BooKShelf booKShelf = new BooKShelf(4);
        booKShelf.appendBook(new Book("java"));
        booKShelf.appendBook(new Book("python"));
        booKShelf.appendBook(new Book("MySQL"));
        booKShelf.appendBook(new Book("C#"));
        Iterator iterator = booKShelf.iterator();
        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println(book.getName());
        }
    }
}
