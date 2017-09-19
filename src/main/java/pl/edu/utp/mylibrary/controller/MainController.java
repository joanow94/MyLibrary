/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.utp.mylibrary.service.AlbumService;
import pl.edu.utp.mylibrary.service.BookService;
import pl.edu.utp.mylibrary.service.MovieService;

/**
 *
 * @author nowakowska joanna
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    AlbumService albumService;

    @Autowired
    BookService bookService;

    @Autowired
    MovieService movieService;

    @RequestMapping("")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("booksAmount", bookService.findAllFromUser().size());
        model.addAttribute("albumsAmount", albumService.findAllFromUser().size());
        model.addAttribute("moviesAmount", movieService.findAllFromUser().size());
        return "home";
    }
}
