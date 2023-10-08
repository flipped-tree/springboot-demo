package com.example.pattern.iterator;

public class BookShelfIterator implements Iterator {

    private final BooKShelf booKShelf;

    private int index;

    public BookShelfIterator(BooKShelf booKShelf) {
        this.booKShelf = booKShelf;
    }

    @Override
    public boolean hasNext() {
        return index < booKShelf.getLength();
    }

    @Override
    public Book next() {
        Book book = booKShelf.getBookAt(index);
        index++;
        return book;
    }
}
