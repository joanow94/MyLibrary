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
import pl.edu.utp.mylibrary.helper.RegisterValidator;
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.service.UserService;

/**
 *
 * @author nowakowska joanna
 */
@Controller
@RequestMapping("/registration")
public class RegisterController {

    @Autowired
    private UserService userService;

    private RegisterValidator registerValidator;

    @RequestMapping("")
    public String getRegisterForm() {
        return "registration";
    }

    @RequestMapping("/registerProcess")
    public String register(Model model, @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) {
        if (registerValidator.isCorrect(firstname, lastname, login, password, confirmPassword)) {
            userService.addUser(new UserInfo(Long.MIN_VALUE, firstname, lastname, login, password));
            //TODO: dodawanie do usera
            return "home";
        } else {
            if (null != registerValidator.validateStringField(firstname)) {
                model.addAttribute("firstnameError", registerValidator.validateStringField(firstname));
            }
            if (null != registerValidator.validateStringField(lastname)) {
                model.addAttribute("lastnameError", registerValidator.validateStringField(lastname));
            }
            if (null != registerValidator.validateStringField(login)) {
                model.addAttribute("loginError", registerValidator.validateStringField(login));
            } else if (null != registerValidator.validateUniqueLogin(login)) {
                model.addAttribute("loginError", registerValidator.validateStringField(login));
            }
            if (null != registerValidator.validateStringField(password)) {
                model.addAttribute("passwordsError", registerValidator.validatePasswords(password, confirmPassword));
            } else if (null != registerValidator.validatePasswords(password, confirmPassword)) {
                model.addAttribute("passwordsError", registerValidator.validatePasswords(password, confirmPassword));
            }
            return "registration";
        }
    }

}
