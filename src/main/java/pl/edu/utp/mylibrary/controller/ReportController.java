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
import pl.edu.utp.mylibrary.model.Book;
import pl.edu.utp.mylibrary.reports.PdfForReport1View;
//import pl.edu.utp.mylibrary.reports.PopularityOfBooksReport;
import pl.edu.utp.mylibrary.service.BookService;

/**
 *
 * @author nowakowska joanna
 */
@Controller
@RequestMapping("/ranking")
public class ReportController {

    @Autowired
    BookService bookService;

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

    /**
     * Raport I - Najpopularnijsze książki
     *
     * @param model
     * @return
     * @throws java.io.IOException
     * @throws com.itextpdf.text.DocumentException
     */
//    @RequestMapping("/generateReport1")
//    public String getReportI(Model model) throws IOException, DocumentException {
////        File file = new File(DEST);
////        file.getParentFile().mkdirs();
////        new PopularityOfBooksReport().createPdf(DEST);
////
//        return "ranking";
//    }

    /**
     * Raport II - Książki - Top 10
     *
     * @return
     */
    @RequestMapping("/generateReport2")
    public String getReportII() {
        return "ranking";
    }

}
