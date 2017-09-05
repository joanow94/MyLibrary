/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.utp.mylibrary.model.Movie;
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.repository.MovieRepository;

/**
 *
 * @author jnowakowska
 */
@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * FindAll Movies
     *
     * @return
     */
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    /**
     * Add Movie
     *
     * @param title
     * @param director
     * @param year
     * @param country
     * @param genre
     */
    public void addMovie(String title, String director, String year, String country, String genre) {
        movieRepository.save(new Movie(Long.MIN_VALUE, title, director, year, country, genre));
    }

    /**
     * Delete Movie
     *
     * @param id
     */
    public void deleteMovie(Long id) {
        movieRepository.delete(id);
    }

    /**
     * Find Movie By Search Term
     *
     * @param searchTerm
     * @return
     */
    public List<Movie> findBySearchTerm(String searchTerm) {
        return movieRepository.findBySearchTerm(searchTerm);
    }

    /**
     * TODO Add Movie To User
     *
     * @param user
     * @param idMovie
     */
    public void addToUser(UserInfo user, Long idMovie) {
        Movie movie = movieRepository.findOne(idMovie);
        Set<Movie> movies = user.getMovies();
        movies.add(movie);
        user.setMovies(movies);
    }

    /**
     * TODO Delete Movie From User
     *
     * @param user
     * @param idMovie
     */
    public void deleteFromUser(UserInfo user, Long idMovie) {
        Set<Movie> movies = user.getMovies();
        Set<Movie> moviesNew = new HashSet<>();
        for (Movie m : movies) {
            if (!m.getId().equals(idMovie)) {
                moviesNew.add(m);
            }
        }
        user.setMovies(moviesNew);
    }

    /**
     * FindAll From User
     *
     * @param user
     * @return
     */
    public Set<Movie> findAllFromUser(UserInfo user) {
        return user.getMovies();
    }
}
