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
@RequestMapping("/rejestracja")
public class RegisterController {

    @RequestMapping("")
    public String getRegisterForm() {
        return "registration";
    }

    @RequestMapping("/zarejestruj")
    public String register(Model model, @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("login") String login, @RequestParam("password") String password) {
        //register()
        //add model atribute
        return "home";
    }

}
