/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.utp.mylibrary.model.UserInfo;

/**
 *
 * @author jnowakowska
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {

    public UserInfo findByFirstname(String firstname);
}
