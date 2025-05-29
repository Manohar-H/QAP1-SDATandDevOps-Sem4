package com.qap1.library;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final List<Book> borrowedBooks;
    private static final int BORROW_LIMIT = 3;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() >= BORROW_LIMIT || !book.isAvailable()) {
            return false;
        }
        book.setAvailable(false);
        borrowedBooks.add(book);
        return true;
    }

    public boolean returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}

