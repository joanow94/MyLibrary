/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.utp.mylibrary.model.Album;

/**
 *
 * @author jnowakowska
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    /**
     * TODO Search Album
     *
     * @param searchTerm
     * @return
     */
    @Query(value = "SELECT a FROM Album a WHERE "
            + "LOWER(a.title) LIKE LOWER(CONCAT(:searchTerm, '%')) OR "
            + "LOWER(a.artist) LIKE LOWER(CONCAT(:searchTerm, '%')) OR "
            + "LOWER(a.year) LIKE LOWER(CONCAT(:searchTerm, '%')) OR "
            + "LOWER(a.genre) LIKE LOWER(CONCAT(:searchTerm, '%'))"
    )
    List<Album> findBySearchTerm(@Param("searchTerm") String searchTerm);

}
