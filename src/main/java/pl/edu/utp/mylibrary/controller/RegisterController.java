/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.controller;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.utp.mylibrary.enums.ErrorInfo;
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

    private static final Logger LOG = Logger.getLogger(RegisterController.class.getName());

    @Autowired
    private UserService userService;

    private RegisterValidator registerValidator;

    @RequestMapping("")
    public String getRegisterForm() {
        return "registration";
    }

    @RequestMapping("/registerProcess")
    public String register(Model model, @RequestParam(name = "firstname", required = false) String firstname, @RequestParam(name = "lastname", required = false) String lastname, @RequestParam(name = "login", required = false) String login, @RequestParam(name = "password", required = false) String password, @RequestParam(name = "confirmPassword", required = false) String confirmPassword) {
        Boolean isError = false;

        if (null == firstname || firstname.isEmpty()) {
            model.addAttribute("firstnameError", ErrorInfo.EMPTY_FIELD.getInfo());
            isError = true;
        }
        if (null == lastname || lastname.isEmpty()) {
            model.addAttribute("lastnameError", ErrorInfo.EMPTY_FIELD.getInfo());
            isError = true;
        }
        if (null == login || login.isEmpty()) {
            model.addAttribute("loginError", ErrorInfo.EMPTY_FIELD.getInfo());
            isError = true;
        } else if (null != validateUniqueLogin(login)) {
            model.addAttribute("loginError", validateUniqueLogin(login));
            isError = true;
        }
        if (null == password || password.isEmpty()) {
            model.addAttribute("passwordsError", ErrorInfo.EMPTY_FIELD.getInfo());
            isError = true;
        } else if (!password.equals(confirmPassword)) {
            model.addAttribute("passwordsError", ErrorInfo.PASSWORDS.getInfo());
            isError = true;
        }
        if (isError) {
            return "registration";
        }
        userService.save(new UserInfo(Long.MAX_VALUE, firstname, lastname, login, password));
        return "home";
    }

    private String validateUniqueLogin(String login) {
        for (UserInfo u : userService.findAll()) {
            if (u.getLogin().equals(login)) {
                return ErrorInfo.LOGIN_UNIQUE.getInfo();
            }
        }
        return null;
    }

}
