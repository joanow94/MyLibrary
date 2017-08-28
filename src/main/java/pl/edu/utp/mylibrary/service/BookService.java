/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.utp.mylibrary.repository.BookRepository;

/**
 *
 * @author jnowakowska
 */
@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    
}
