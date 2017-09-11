/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nowakowska joanna
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("")
    public String index(Model model) {
        return "index";
    }
    
    @RequestMapping("/home")
    public String home(Model model) {
        return "home";
    }
}
