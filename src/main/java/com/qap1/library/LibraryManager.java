package com.qap1.library;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryManager {
    private final List<Book> books;
    private final List<User> users;

    public LibraryManager() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public List<Book> searchByTitle(String keyword) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public User getUserByName(String name) {
        return users.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
