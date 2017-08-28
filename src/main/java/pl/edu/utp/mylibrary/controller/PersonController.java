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
import pl.edu.utp.mylibrary.model.Person;
import pl.edu.utp.mylibrary.service.PersonService;

/**
 *
 * @author jnowakowska
 */
@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/persons")
    public String persons(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "persons";
    }

    @RequestMapping("/signIn")
    public String signIn(Model model, @RequestParam("login") String login, @RequestParam("password") String password) {
        model.addAttribute("person", personService.signIn(login, password));
        return "person";
    }

    @RequestMapping("/addPerson")
    public String signUp(Model model, @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("login") String login, @RequestParam("password") String password) {
        model.addAttribute("newPerson", personService.signUp(new Person(Long.MIN_VALUE, firstname, lastname, login, password)));
        return "newPerson";
    }
}
