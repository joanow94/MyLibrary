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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.utp.mylibrary.enums.ErrorInfo;
import pl.edu.utp.mylibrary.helper.ItemValidator;
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.service.MovieService;

/**
 *
 * @author jnowakowska
 */
@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    private ItemValidator itemValidator;

    //TODO: tymczasowo przed logowaniem 
    UserInfo user;

    @RequestMapping("")
    public String movies(Model model) {
        model.addAttribute("userMovies", movieService.findAllFromUser(user));
        return "movies";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMovie(Model model, @PathVariable("id") String id) {
        movieService.deleteFromUser(user, Long.parseLong(id));
        //TODO: co z tym odświeżaniem
        model.addAttribute("userMovies", movieService.findAllFromUser(user));
        return "movies";
    }

    @RequestMapping("/search")
    public String searchMovie(Model model) {
        model.addAttribute("allMovies", movieService.findAll());
        return "searchMovie";
    }

    @RequestMapping("/search/results")
    public String findBySearchTerm(Model model, @RequestParam("searchTerm") String searchTerm) {
        model.addAttribute("resultMovies", movieService.findBySearchTerm(searchTerm));
        return "searchMovie";
    }

    @RequestMapping("/search/add/{id}")
    public String addMovieToUser(Model model, @PathVariable("id") String id) {
        movieService.addToUser(user, Long.parseLong(id));
        return "searchMovie";
    }

    @RequestMapping("/addNew")
    public String getAddNewMovieForm() {
        return "addNewMovie";
    }

    @RequestMapping("/addNew/add")
    public String addNewMovie(Model model, @RequestParam("title") String title, @RequestParam("director") String director, @RequestParam("year") String year, @RequestParam("country") String country, @RequestParam("genre") String genre) {
        if (itemValidator.isCorrectMovie(title, director, year, country, genre)) {
            movieService.addMovie(title, director, year, country, genre);
            //TODO: dodac do usera
            model.addAttribute("userMovies", movieService.findAllFromUser(user));
            return "movies";
        } else {
            if (null != itemValidator.validateField(title)) {
                model.addAttribute("titleError", itemValidator.validateField(title));
            }
            if (null != itemValidator.validateField(director)) {
                model.addAttribute("directorError", itemValidator.validateField(director));
            }
            if (null != itemValidator.validateField(country)) {
                model.addAttribute("countryError", itemValidator.validateField(country));
            }
            if (null != itemValidator.validateField(year)) {
                model.addAttribute("yearError", itemValidator.validateField(year));
            }
            if (null != itemValidator.validateField(genre)) {
                model.addAttribute("genreError", itemValidator.validateField(genre));
            }
            return "addNewMovie";
        }
    }
}
