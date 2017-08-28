/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.utp.mylibrary.model.Album;

/**
 *
 * @author jnowakowska
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

}
