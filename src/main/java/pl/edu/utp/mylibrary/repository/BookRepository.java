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
import pl.edu.utp.mylibrary.model.Book;

/**
 *
 * @author jnowakowska
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT b FROM Book b WHERE "
            + "LOWER(b.title) LIKE LOWER(CONCAT(:searchTerm, '%')) OR "
            + "LOWER(b.author) LIKE LOWER(CONCAT(:searchTerm, '%')) OR "
            + "LOWER(b.publisher) LIKE LOWER(CONCAT(:searchTerm, '%'))"
    )
    List<Book> findBySearchTerm(@Param("searchTerm") String searchTerm);
}
