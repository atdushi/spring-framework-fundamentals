package com.virtualpairprogrammers.avalon.services;

import com.virtualpairprogrammers.avalon.data.BookNotFoundException;
import com.virtualpairprogrammers.avalon.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Вместо Spring можно использовать аннотации из CDI
 * Но Named звучит не так хорошо как Service или Component
 */
@Transactional
@Named
public class PurchasingServiceImpl implements PurchasingService {
    @Inject
    private AccountsService accounts;
    @Inject
    private BookService books;

    @Transactional(
            rollbackFor = {CustomerCreditExcededException.class, BookNotFoundException.class},
            timeout = 10,
            isolation = Isolation.SERIALIZABLE
    )
    public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExcededException {
        // find the correct book
        Book requiredBook = books.getBookByIsbn(isbn);

        // delete the book from stock
        books.deleteFromStock(requiredBook);

        // now raise the invoice
        accounts.raiseInvoice(requiredBook);

//        try {
//            accounts.raiseInvoice(requiredBook);
//        } catch (CustomerCreditExcededException e) {
//            // скажет spring вызвать rollback в конце метода. уродливый вариант
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            throw e;
//        }
    }
}
