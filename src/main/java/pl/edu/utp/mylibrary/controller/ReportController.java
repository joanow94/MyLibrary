/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.controller;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.utp.mylibrary.model.Album;
import pl.edu.utp.mylibrary.model.Book;
import pl.edu.utp.mylibrary.model.Movie;
import pl.edu.utp.mylibrary.reports.PdfForReport1View;
import pl.edu.utp.mylibrary.reports.PdfForReport2View;
import pl.edu.utp.mylibrary.reports.PdfForReport3View;
import pl.edu.utp.mylibrary.service.AlbumService;
//import pl.edu.utp.mylibrary.reports.PopularityOfBooksReport;
import pl.edu.utp.mylibrary.service.BookService;
import pl.edu.utp.mylibrary.service.MovieService;

/**
 *
 * @author nowakowska joanna
 */
@Controller
@RequestMapping("/ranking")
public class ReportController {

    @Autowired
    BookService bookService;

    @Autowired
    AlbumService albumService;

    @Autowired
    MovieService movieService;

    @RequestMapping("")
    public String ranking() {
        return "ranking";
    }

    @RequestMapping(path = "/report1", method = RequestMethod.GET)
    public ModelAndView report1() {
        Map<String, Object> model = new HashMap<>();
        List<Book> books = bookService.getSortByPopularity();
        model.put("books", books);
        return new ModelAndView(new PdfForReport1View(), model);
    }

    @RequestMapping(path = "/report2", method = RequestMethod.GET)
    public ModelAndView report2() {
        Map<String, Object> model = new HashMap<>();
        List<Album> albums = albumService.getSortByPopularity();
        model.put("albums", albums);
        return new ModelAndView(new PdfForReport2View(), model);
    }

    @RequestMapping(path = "/report3", method = RequestMethod.GET)
    public ModelAndView report3() {
        Map<String, Object> model = new HashMap<>();
        List<Movie> movies = movieService.getSortByPopularity();
        model.put("movies", movies);
        return new ModelAndView(new PdfForReport3View(), model);
    }
}
