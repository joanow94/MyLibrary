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
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.utp.mylibrary.enums.ErrorInfo;
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.service.AlbumService;
import pl.edu.utp.mylibrary.service.BookService;
import pl.edu.utp.mylibrary.service.MovieService;
import pl.edu.utp.mylibrary.service.UserService;

/**
 *
 * @author nowakowska joanna
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    AlbumService albumService;

    @Autowired
    BookService bookService;

    @Autowired
    MovieService movieService;

    public UserInfo user;

    @RequestMapping("")
    public String login(Model model, @RequestParam("login") String login, @RequestParam("password") String password) {
        this.user = userService.login(login, password);
        if (null == user) {
            model.addAttribute("loginError", ErrorInfo.LOGIN_PASSWORD.getInfo());
            model.addAttribute("loginForm", "#loginForm");
            return "index";
        } else {
            //TODO: To tak nie działa
            model.addAttribute("user", user);
            model.addAttribute("userAlbums", albumService.findAllFromUser(user));
            model.addAttribute("userBooks", bookService.findAllFromUser(user));
            model.addAttribute("userMovies", movieService.findAllFromUser(user));
            return "home";
        }
    }
}
