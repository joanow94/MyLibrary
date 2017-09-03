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
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.service.UserService;

/**
 *
 * @author jnowakowska
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

   @RequestMapping("/home")
    public String home(Model model) {
        return "home";
    }
    
}
