package com.qap1.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryManagerTest {

    private LibraryManager library;
    private Book book1;
    private Book book2;
    private User user;

    @BeforeEach
    void setUp() {
        library = new LibraryManager();
        book1 = new Book("Clean Code", "Robert C. Martin");
        book2 = new Book("Effective Java", "Joshua Bloch");

        user = new User("Alice");

        library.addBook(book1);
        library.addBook(book2);
        library.registerUser(user);
    }

    @Test
    void testBookSearchByTitle() {
        List<Book> results = library.searchByTitle("Java");
        assertEquals(1, results.size());
        assertEquals("Effective Java", results.get(0).getTitle());
    }

    @Test
    void testBookBorrowingSuccess() {
        boolean borrowed = user.borrowBook(book1);
        assertTrue(borrowed);
        assertFalse(book1.isAvailable());
        assertEquals(1, user.getBorrowedBooks().size());
    }

    @Test
    void testBorrowLimitExceeded() {
        user.borrowBook(book1);
        user.borrowBook(book2);
        Book book3 = new Book("Design Patterns", "GoF");
        library.addBook(book3);
        user.borrowBook(book3);

        Book book4 = new Book("Refactoring", "Martin Fowler");
        library.addBook(book4);
        boolean result = user.borrowBook(book4);

        assertFalse(result);
        assertEquals(3, user.getBorrowedBooks().size());
    }

    @Test
    void testReturnBook() {
        user.borrowBook(book1);
        boolean returned = user.returnBook(book1);

        assertTrue(returned);
        assertTrue(book1.isAvailable());
        assertEquals(0, user.getBorrowedBooks().size());
    }
}