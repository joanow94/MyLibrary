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
import pl.edu.utp.mylibrary.model.Movie;
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

    @RequestMapping("")
    public String movies(Model model) {
        model.addAttribute("movies", movieService.findAllFromUser());
        return "movies";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMovie(Model model, @PathVariable("id") String id) {
        movieService.deleteFromUser(Long.parseLong(id));
        model.addAttribute("movies", movieService.findAllFromUser());
        return "movies";
    }

    @RequestMapping("/search")
    public String searchMovie(Model model) {
        model.addAttribute("allMovies", movieService.findAll());
        return "searchMovie";
    }

    @RequestMapping("/search/results")
    public String findBySearchTerm(Model model, @RequestParam("searchTerm") String searchTerm) {
        if (searchTerm.isEmpty() || searchTerm == null) {
            model.addAttribute("allMovies", movieService.findAll());
        } else {
            model.addAttribute("allMovies", movieService.findBySearchTerm(searchTerm));
        }
        return "searchMovie";
    }

    @RequestMapping("/search/add/{id}")
    public String addMovieToUser(Model model, @PathVariable("id") String id) {
        movieService.addToUser(Long.parseLong(id));
        return "searchMovie";
    }

    @RequestMapping("/addNew")
    public String getAddNewMovieForm() {
        return "addMovie";
    }

    @RequestMapping("/addNew/add")
    public String addNewMovie(Model model, @RequestParam("title") String title, @RequestParam("director") String director, @RequestParam("year") String year, @RequestParam("country") String country, @RequestParam("genre") String genre) {
        Boolean isError = false;
        if (null == title || title.isEmpty()) {
            model.addAttribute("titleError", ErrorInfo.EMPTY_FIELD.getInfo());
            isError = true;
        }
        if (null == director || director.isEmpty()) {
            model.addAttribute("directorError", ErrorInfo.EMPTY_FIELD.getInfo());
            isError = true;
        }
        if (null == country || country.isEmpty()) {
            model.addAttribute("countryError", ErrorInfo.EMPTY_FIELD.getInfo());
            isError = true;
        }
        if (null == year || year.isEmpty()) {
            model.addAttribute("yearError", ErrorInfo.EMPTY_FIELD.getInfo());
            isError = true;
        }
        if (null == genre || genre.isEmpty()) {
            model.addAttribute("genreError", ErrorInfo.EMPTY_FIELD.getInfo());
            isError = true;
        }
        if (isError) {
            return "addMovie";
        }
        Movie movie = new Movie(null, title, director, year, country, genre);
        movieService.addMovie(movie);
        movieService.addToUser(movie);
        model.addAttribute("movies", movieService.findAllFromUser());
        return "movies";
    }
}
