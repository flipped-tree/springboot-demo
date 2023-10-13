package com.example.pattern.iterator;

public class BookShelfIterator implements Iterator {
    private final BookShelf bookShelf;
    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    public boolean hasNext() {
        return index < bookShelf.getLength();
    }

    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }
}
