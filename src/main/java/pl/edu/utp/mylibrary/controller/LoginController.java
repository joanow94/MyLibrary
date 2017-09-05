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

    @RequestMapping("")
    public String getLoginForm() {
        return "login";
    }

    @RequestMapping("/loginProcess")
    public String login(Model model, @RequestParam("login") String login, @RequestParam("password") String password) {
        if (null == userService.login(login, password)) {
            model.addAttribute("loginError", ErrorInfo.LOGIN);
            return "login";
        } else {
            //TODO: To tak nie działa
//            model.addAttribute("user", userService.login(login, password));
            return "home";
        }
    }
}
