package com.virtualpairprogrammers.avalon.services;

import com.virtualpairprogrammers.avalon.data.BookDao;
import com.virtualpairprogrammers.avalon.data.BookNotFoundException;
import com.virtualpairprogrammers.avalon.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This is the production implementation - it will be calling a "real" database.
 */

@Transactional(propagation = Propagation.REQUIRED)
@Service("bookService")
public class BookServiceProductionImpl implements BookService {
    @Autowired
    private BookDao dao;

    @Override
    public List<Book> getAllBooksByAuthor(String author) {
        return dao.findBooksByAuthor(author);
    }

    @Override
    public List<Book> getAllRecommendedBooks(String userId) {
        throw new UnsupportedOperationException();
    }

    @Transactional(readOnly = true)
    public Book getBookByIsbn(String isbn) throws BookNotFoundException {
        return dao.findByIsbn(isbn);
    }

    @Override
    public List<Book> getEntireCatalogue() {
        return dao.allBooks();
    }

    @Override
    public void registerNewBook(Book newBook) {
        // we want this book to be put into the database.
        dao.create(newBook);
    }

    @Override
    public void deleteFromStock(Book oldBook) {
        dao.delete(oldBook);
    }

}
