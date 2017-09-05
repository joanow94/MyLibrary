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
        model.addAttribute("userBooks", bookService.findAllFromUser(user));
        return "books";
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(Model model, @PathVariable("id") String id) {
        bookService.deleteFromUser(user, Long.parseLong(id));
        //TODO: co z tym odświeżaniem
        model.addAttribute("userBooks", bookService.findAllFromUser(user));
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
        bookService.addToUser(user, Long.parseLong(id));
        return "searchBook";
    }

    @RequestMapping("/addNew")
    public String getAddNewBookForm() {
        return "addNewBook";
    }

    @RequestMapping("/addNew/add")
    public String addNewBook(Model model, @RequestParam("title") String title, @RequestParam("author") String author, @RequestParam("publisher") String publisher) {
        List<ErrorInfo> errors = itemValidator.validateBook(title, author, publisher);
        if (null == errors || errors.isEmpty()) {
            bookService.addBook(title, author, publisher);
            //TODO: jak dodać do użytkownika ??
            //bookService.addToUser(user, Long.parseLong(id));
            model.addAttribute("userBooks", bookService.findAllFromUser(user));
            return "books";
        } else {
            model.addAttribute("validationBookErrors", errors);
            return "addNewBook";
        }

    }

}