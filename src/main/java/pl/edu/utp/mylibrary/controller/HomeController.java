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
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.service.AlbumService;
import pl.edu.utp.mylibrary.service.BookService;
import pl.edu.utp.mylibrary.service.MovieService;

/**
 *
 * @author nowakowska joanna
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    AlbumService albumService;

    @Autowired
    BookService bookService;

    @Autowired
    MovieService movieService;

    //TODO: tymczasowo przed logowaniem 
    UserInfo user;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("")
    public String home(Model model) {
        model.addAttribute("userAlbums", albumService.findAllFromUser(user));
        model.addAttribute("userBooks", bookService.findAllFromUser(user));
        model.addAttribute("userMovies", movieService.findAllFromUser(user));
        return "home";
    }

}
