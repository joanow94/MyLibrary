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
@RequestMapping("/ranking")
public class ReportController {
    
    @RequestMapping("")
    public String ranking(){
        return "ranking";
    }
    
    /**
     * Raport I - Najpopularnijsze książki
     * @param model
     * @return 
     */
    @RequestMapping("/generateReportI")
    public String getReportI(Model model){
        return "ranking";
    }
    
    /**
     * Raport II - Książki - Top 10
     * @return 
     */
    @RequestMapping("/generateReportII")
    public String getReportII(){
        return "ranking";
    }
    
}
