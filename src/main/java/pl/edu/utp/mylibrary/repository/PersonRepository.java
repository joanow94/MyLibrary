/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.utp.mylibrary.model.Person;

/**
 *
 * @author jnowakowska
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    public Person signUp(Person person);

    public Person signIn(String login, String password);

    @Override
    public List<Person> findAll();

    @Override
    public void delete(Person person);
}
