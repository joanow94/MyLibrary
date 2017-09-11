/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.utp.mylibrary.enums.ErrorInfo;
import pl.edu.utp.mylibrary.helper.ItemValidator;
import pl.edu.utp.mylibrary.model.Book;
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.service.BookService;

/**
 *
 * @author jnowakowska
 */
@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    private ItemValidator itemValidator;

    //TODO: tymczasowo przed logowaniem 
    UserInfo user;

    @RequestMapping("")
    public String books(Model model) {
//        model.addAttribute("books", bookService.findAllFromUser(user));
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(Model model, @PathVariable("id") String id) {
//        bookService.deleteFromUser(user, Long.parseLong(id));
        bookService.deleteBook(Long.parseLong(id));
        //TODO: co z tym odświeżaniem
        model.addAttribute("books", bookService.findAll());
//        model.addAttribute("books", bookService.findAllFromUser(user));
        return "books";
    }

    @RequestMapping("/search")
    public String searchBook(Model model) {
        model.addAttribute("allBooks", bookService.findAll());
        return "searchBook";
    }

    @RequestMapping("/search/results")
    public String findBySearchTerm(Model model, @RequestParam("searchTerm") String searchTerm) {
        model.addAttribute("resultBooks", bookService.findBySearchTerm(searchTerm));
        return "searchBook";
    }

    @RequestMapping("/search/add/{id}")
    public String addBookToUser(Model model, @PathVariable("id") String id) {
        bookService.addToUser(user, bookService.findOne(Long.parseLong(id)));
        return "searchBook";
    }

    @RequestMapping("/addNew")
    public String getAddNewBookForm() {
        return "addBook";
    }

    @RequestMapping("/addNew/add")
    public String addNewBook(Model model, @RequestParam("title") String title, @RequestParam("author") String author, @RequestParam("publisher") String publisher) {
        if (itemValidator.isCorrectBook(title, author, publisher)) {
            Book book = new Book(null, title, author, publisher);
            bookService.save(book);
            bookService.addToUser(user, book);
            model.addAttribute("books", bookService.findAllFromUser(user));
            return "books";
        } else {
            if (null != itemValidator.validateField(title)) {
                model.addAttribute("titleError", itemValidator.validateField(title));
            }
            if (null != itemValidator.validateField(author)) {
                model.addAttribute("authorError", itemValidator.validateField(author));
            }
            if (null != itemValidator.validateField(publisher)) {
                model.addAttribute("publisherError", itemValidator.validateField(publisher));
            }
            return "addBook";
        }
    }

}
