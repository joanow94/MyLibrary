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

    @Autowired
    private UserService userService;

    private RegisterValidator registerValidator;

    @RequestMapping("")
    public String getRegisterForm() {
        return "registration";
    }

    @RequestMapping("/registerProcess")
    public String register(Model model, @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) {
        List<ErrorInfo> errors = registerValidator.execute(firstname, lastname, login, password, confirmPassword);
        if (null == errors || errors.isEmpty()) {
            userService.addUser(new UserInfo(Long.MIN_VALUE, firstname, lastname, login, password));
//            model.addAttribute("user", user);
            //TODO: czy trzeba znów dodawać books itp...
            return "home";
        } else {
            model.addAttribute("ValidationErrors", errors);
            return "registration";
        }
    }

}
