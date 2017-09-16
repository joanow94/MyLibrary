/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.utp.mylibrary.model.Book;
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.repository.BookRepository;
import pl.edu.utp.mylibrary.repository.UserRepository;

/**
 *
 * @author jnowakowska
 */
@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * FindAll Books
     *
     * @return
     */
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findOne(Long id) {
        return bookRepository.findOne(id);
    }

    /**
     * Add Book
     *
     * @param <S>
     * @param s
     */
    public <S extends Book> S save(S s) {
        return bookRepository.save(s);
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
    public void addToUser(Book book) {
        UserInfo admin = userRepository.findByFirstname("adminI");
        Set<Book> books = admin.getBooks();
        books.add(book);
        admin.setBooks(books);
    }

    public void addToUser(Long idBook) {
        UserInfo admin = userRepository.findByFirstname("adminI");
        Book book = bookRepository.findOne(idBook);
        Set<Book> books = admin.getBooks();
        books.add(book);
        admin.setBooks(books);
    }

    /**
     * Delete Book From User
     *
     * @param user
     * @param id
     */
    public void deleteFromUser(Long id) {
        UserInfo admin = userRepository.findByFirstname("adminI");
        Set<Book> books = admin.getBooks();
        Set<Book> booksNew = new HashSet<>();
        for (Book b : books) {
            if (!b.getId().equals(id)) {
                booksNew.add(b);
            }
        }
        admin.setBooks(booksNew);
    }

    /**
     * Find All Books From User
     *
     * @param user
     * @return
     */
    public Set<Book> findAllFromUser() {
        return userRepository.findByFirstname("adminI").getBooks();
    }

    /**
     * getSortByPopularity
     *
     * @return
     */
    public List<Book> getSortByPopularity() {
        List<Book> books = bookRepository.findAll();
        List<Book> sortBooks = new ArrayList<>();
        List<Book> sortBooks2 = new ArrayList<>();
        Map<Book, Integer> map = new HashMap<>();
        for (Book b : books) {
            map.put(b, getNumberOfOwners(b));
        }
        map = sortByValue(map);

        for (Map.Entry entry : map.entrySet()) {
            sortBooks.add((Book) entry.getKey());
        }

        for (int i = sortBooks.size() - 1; i >= 0; i--) {
            sortBooks2.add(sortBooks.get(i));
        }

        return sortBooks2;
    }

    private static Map<Book, Integer> sortByValue(Map<Book, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<Book, Integer>> list
                = new LinkedList<Map.Entry<Book, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Book, Integer>>() {
            public int compare(Map.Entry<Book, Integer> o1,
                    Map.Entry<Book, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Book, Integer> sortedMap = new LinkedHashMap<Book, Integer>();
        for (Map.Entry<Book, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private int getNumberOfOwners(Book book) {
        int numberOfOwner = 0;
        List<UserInfo> users = userRepository.findAll();
        for (UserInfo u : users) {
            for (Book b : u.getBooks()) {
                if (b.equals(book)) {
                    numberOfOwner++;
                }
            }
        }
        return numberOfOwner;
    }
}
