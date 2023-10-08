package com.example.pattern.iterator;

public class BooKShelf implements Aggregate {
    private final Book[] books;
    private int last;

    public BooKShelf(int maxsize) {
        this.books = new Book[maxsize];
    }

    public void appendBook(Book book) {
        this.books[last] = book;
        last++;
    }

    public Book getBookAt(int index) {
        return books[index];
    }

    public int getLength() {
        return last;
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
