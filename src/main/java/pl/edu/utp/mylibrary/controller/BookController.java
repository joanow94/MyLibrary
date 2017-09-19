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

    @RequestMapping("")
    public String books(Model model) {
        model.addAttribute("books", bookService.findAllFromUser());
        return "books";
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(Model model, @PathVariable("id") String id) {
        bookService.deleteFromUser(Long.parseLong(id));
        model.addAttribute("books", bookService.findAllFromUser());
        return "books";
    }

    @RequestMapping("/search")
    public String searchBook(Model model) {
        model.addAttribute("allBooks", bookService.findAll());
        return "searchBook";
    }

    @RequestMapping("/search/results")
    public String findBySearchTerm(Model model, @RequestParam("searchTerm") String searchTerm) {
        if (searchTerm.isEmpty() || searchTerm == null) {
            model.addAttribute("allBooks", bookService.findAll());
        } else {
            model.addAttribute("allBooks", bookService.findBySearchTerm(searchTerm));
        }
        return "searchBook";
    }

    @RequestMapping("/search/add/{id}")
    public String addBookToUser(Model model, @PathVariable("id") String id) {
        bookService.addToUser(bookService.findOne(Long.parseLong(id)));
        model.addAttribute("allBooks", bookService.findAll());
        return "searchBook";
    }

    @RequestMapping("/addNew")
    public String getAddNewBookForm() {
        return "addBook";
    }

    @RequestMapping("/addNew/add")
    public String addNewBook(Model model, @RequestParam("title") String title, @RequestParam("author") String author, @RequestParam("publisher") String publisher) {
        Boolean isError = false;
        if (null == title || title.isEmpty()) {
            model.addAttribute("titleError", ErrorInfo.EMPTY_FIELD.getInfo());
            isError = true;
        }
        if (null == author || author.isEmpty()) {
            model.addAttribute("authorError", ErrorInfo.EMPTY_FIELD.getInfo());
            isError = true;
        }
        if (null == publisher || publisher.isEmpty()) {
            model.addAttribute("publisherError", ErrorInfo.EMPTY_FIELD.getInfo());
            isError = true;
        }
        if (isError) {
            return "addBook";
        }

        Book book = new Book(null, title, author, publisher);
        bookService.save(book);
        bookService.addToUser(book);

        model.addAttribute("books", bookService.findAllFromUser());
        return "books";
    }

}
