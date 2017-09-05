/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.utp.mylibrary.model.Book;
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.repository.BookRepository;

/**
 *
 * @author jnowakowska
 */
@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * FindAll Books
     *
     * @return
     */
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * Add Book
     *
     * @param title
     * @param author
     * @param publisher
     */
    public void addBook(String title, String author, String publisher) {
        bookRepository.save(new Book(Long.MIN_VALUE, title, author, publisher));
    }

    /**
     * Delete Book
     *
     * @param id
     */
    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }

    /**
     * Find Book By Search Term
     *
     * @param searchTerm
     * @return
     */
    public List<Book> findBySearchTerm(String searchTerm) {
        return bookRepository.findBySearchTerm(searchTerm);
    }

    /**
     * Add Book To User
     *
     * @param user
     * @param id
     */
    public void addToUser(UserInfo user, Long id) {
        Book book = bookRepository.findOne(id);
        Set<Book> books = user.getBooks();
        books.add(book);
        user.setBooks(books);
    }

    /**
     * Delete Book From User
     *
     * @param user
     * @param id
     */
    public void deleteFromUser(UserInfo user, Long id) {
        Set<Book> books = user.getBooks();
        Set<Book> booksNew = new HashSet<>();
        for (Book b : books) {
            if (!b.getId().equals(id)) {
                booksNew.add(b);
            }
        }
        user.setBooks(booksNew);
    }

    /**
     * Find All Books From User
     *
     * @param user
     * @return
     */
    public Object findAllFromUser(UserInfo user) {
        return user.getBooks();
    }

}
