/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.utp.mylibrary.model.Album;
import pl.edu.utp.mylibrary.model.Book;
import pl.edu.utp.mylibrary.repository.AlbumRepository;

/**
 *
 * @author jnowakowska
 */
@Service
@Transactional
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;
    
    public List<Album> findAll(){
        return albumRepository.findAll();
    }
    
}
