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
import pl.edu.utp.mylibrary.model.Movie;

/**
 *
 * @author jnowakowska
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT m FROM Movie m WHERE "
            + "LOWER(m.title) LIKE LOWER(CONCAT(:searchTerm, '%')) OR "
            + "LOWER(m.director) LIKE LOWER(CONCAT(:searchTerm, '%')) OR "
            + "LOWER(m.year) LIKE LOWER(CONCAT(:searchTerm, '%')) OR "
            + "LOWER(m.country) LIKE LOWER(CONCAT(:searchTerm, '%')) OR "
            + "LOWER(m.genre) LIKE LOWER(CONCAT(:searchTerm, '%'))"
    )
    List<Movie> findBySearchTerm(@Param("searchTerm") String searchTerm);
}
