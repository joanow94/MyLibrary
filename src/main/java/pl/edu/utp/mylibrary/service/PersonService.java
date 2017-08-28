/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.utp.mylibrary.model.Person;
import pl.edu.utp.mylibrary.repository.PersonRepository;

/**
 *
 * @author jnowakowska
 */
@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

//    @PostConstruct
//    public void init() {
//        personRepository.save(Arrays.asList(
//                new Person(Long.MIN_VALUE, "Jan", "Kowalski", "jank", "1"),
//                new Person(Long.MIN_VALUE, "Jan2", "Kowalski2", "jank2", "2")
//        ));
//    }
    /**
     * FindAll Persons
     *
     * @return
     */
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    /**
     * Delete person
     *
     * @param person
     */
    public void delete(Person person) {
        personRepository.delete(person);
    }

    /**
     * Sign In
     *
     * @param login
     * @param password
     * @return
     */
    public Person signIn(String login, String password) {
        for (Person p : personRepository.findAll()) {
            if (p.getLogin().equals(login) && p.getPassword().equals(password)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Sign Up
     *
     * @param person
     * @return
     */
    public Person signUp(Person person) {
        personRepository.save(person);
        return person;
    }
}
