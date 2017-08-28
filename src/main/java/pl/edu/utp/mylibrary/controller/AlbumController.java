/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.utp.mylibrary.service.AlbumService;

/**
 *
 * @author jnowakowska
 */
@Controller
public class AlbumController {

    @Autowired
    private AlbumService albumService;
}
