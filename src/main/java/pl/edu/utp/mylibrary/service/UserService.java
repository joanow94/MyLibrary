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
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.repository.UserRepository;

/**
 *
 * @author jnowakowska
 */
@Service
@Transactional
public class UserService{

    @Autowired
    private UserRepository userRepository;

    /**
     * FindAll Persons
     *
     * @return
     */
    public List<UserInfo> findAll() {
        return userRepository.findAll();
    }

    /**
     * Delete person
     *
     * @param userInfo
     */
    public void delete(UserInfo userInfo) {
        userRepository.delete(userInfo);
    }

    /**
     * Login
     *
     * @param login
     * @param password
     * @return
     */
//    public UserInfo login(String login, String password) {
//        for (UserInfo p : personRepository.findAll()) {
//            if (p.getLogin().equals(login) && p.getPassword().equals(password)) {
//                return p;
//            }
//        }
//        return null;
//    }

//    /**
//     * Sign Up
//     *
//     * @param person
//     * @return
//     */
//    public Person signUp(Person person) {
//        personRepository.save(person);
//        return person;
//    }
}
