package com.virtualpairprogrammers.avalon.test;

import com.virtualpairprogrammers.avalon.domain.Book;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void testBooksWithTheSameISBNNumberAreConsideredTheSameProduct() {
        Book bookOne = new Book("12345", "java", "josh", 10.99);
        Book bookTwo = new Book("12345", "java", "josh", 10.99);

        boolean result = bookOne.equals(bookTwo);

        assertTrue(result);
    }
}
