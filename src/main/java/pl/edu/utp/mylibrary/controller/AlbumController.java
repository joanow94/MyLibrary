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
import pl.edu.utp.mylibrary.model.Album;
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.service.AlbumService;

/**
 *
 * @author jnowakowska
 */
@Controller
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    private ItemValidator itemValidator;

    //TODO: tymczasowo przed logowaniem 
    UserInfo user;

    @RequestMapping("")
    public String albums(Model model) {

        model.addAttribute("albums", albumService.findAllFromUser());

        return "albums";
    }

    @RequestMapping("/delete/{id}")
    public String deleteAlbum(Model model, @PathVariable("id") String id) {
        albumService.deleteFromUser(Long.parseLong(id));
        //TODO: co z tym odświeżaniem
        model.addAttribute("albums", albumService.findAllFromUser());
        return "albums";
    }

    @RequestMapping("/search")
    public String searchAlbum(Model model) {
        model.addAttribute("allAlbums", albumService.findAll());
        return "searchAlbum";
    }

    @RequestMapping("/search/results")
    public String findBySearchTerm(Model model, @RequestParam("searchTerm") String searchTerm) {
        if (searchTerm.isEmpty() || searchTerm == null) {
            model.addAttribute("allAlbums", albumService.findAll());
        } else {
            model.addAttribute("allAlbums", albumService.findBySearchTerm(searchTerm));
        }
        return "searchAlbum";
    }

    @RequestMapping("/search/add/{id}")
    public String addAlbumToUser(Model model, @PathVariable("id") String id) {
        albumService.addToUser(Long.parseLong(id));
        model.addAttribute("allAlbums", albumService.findAll());
        return "searchAlbum";
    }

    @RequestMapping("/addNew")
    public String getAddNewAlbumForm() {
        return "addAlbum";
    }

    @RequestMapping("/addNew/add")
    public String addNewAlbum(Model model, @RequestParam("title") String title, @RequestParam("artist") String artist, @RequestParam("year") String year, @RequestParam("genre") String genre) {
        Boolean isError = false;

//        if (null != itemValidator.validateField(title)) {
//            model.addAttribute("titleError", itemValidator.validateField(title));
//            isError = true;
//        }
//        if (null != itemValidator.validateField(artist)) {
//            model.addAttribute("artistError", itemValidator.validateField(artist));
//            isError = true;
//        }
//        if (null != itemValidator.validateField(year)) {
//            model.addAttribute("yearError", itemValidator.validateField(year));
//            isError = true;
//        }
//        if (null != itemValidator.validateField(genre)) {
//            model.addAttribute("genreError", itemValidator.validateField(genre));
//            isError = true;
//        }
//        if (isError) {
//            return "addAlbum";
//        }
        Album album = new Album(null, title, artist, year, genre);
        albumService.addAlbum(album);
        albumService.addToUser(album);

        model.addAttribute("albums", albumService.findAllFromUser());
        return "albums";
    }
}
