/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.service;

import java.util.HashSet;
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
    
    public Book addBook(String title, String author, String publisher){
        Book book = new Book(Long.MIN_VALUE, title, author, publisher);
        bookRepository.save(book);
        return book;
    }

    public Book addBookToUser(UserInfo user, Long id){
        Book book = bookRepository.findOne(id);
        Set<Book> books = user.getBooks();
        books.add(book);
        return book;
    }
    
    public void deleteBookFromUser(UserInfo user, Long id){
        Set<Book> books = user.getBooks();
        Set<Book> booksNew = new HashSet<>();
        for(Book b : books){
            if(!b.getId().equals(id)){
                booksNew.add(b);
            }
        }
        user.setBooks(booksNew);
    }
    
}
