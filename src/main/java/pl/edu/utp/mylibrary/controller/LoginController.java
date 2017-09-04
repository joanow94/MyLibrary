/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author nowakowska joanna
 */
@Controller
@RequestMapping("/logowanie")
public class LoginController {

    @RequestMapping("")
    public String getLoginForm() {
        return "login";
    }

    @RequestMapping("/zaloguj")
    public String login(Model model, @RequestParam("login") String login, @RequestParam("password") String password) {
        //userLogin
        //addAtribute
        return "home";
    }
}
