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
 * @author jnowakowska TODO: !
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserInfo findByFirstname(String firstname) {
        return userRepository.findByFirstname(firstname);
    }
    
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
     * addUser
     *
     * @param user
     */
//    public void addUser(UserInfo user) {
//        userRepository.save(user);
//    }

    public <S extends UserInfo> S save(S s) {
        return userRepository.save(s);
    }
    

    /**
     * Login
     *
     * @param login
     * @param password
     * @return
     */
    public UserInfo login(String login, String password) {
        for (UserInfo u : userRepository.findAll()) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}
