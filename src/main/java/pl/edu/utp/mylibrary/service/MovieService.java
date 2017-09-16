/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.utp.mylibrary.model.Movie;
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.repository.MovieRepository;
import pl.edu.utp.mylibrary.repository.UserRepository;

/**
 *
 * @author jnowakowska
 */
@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

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
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
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
    public void addToUser(Long idMovie) {
        UserInfo admin = userRepository.findByFirstname("adminI");
        Movie movie = movieRepository.findOne(idMovie);
        Set<Movie> movies = admin.getMovies();
        movies.add(movie);
        admin.setMovies(movies);
    }

    public void addToUser(Movie movie) {
        UserInfo admin = userRepository.findByFirstname("adminI");
        Set<Movie> movies = admin.getMovies();
        movies.add(movie);
        admin.setMovies(movies);
    }

    /**
     * TODO Delete Movie From User
     *
     * @param user
     * @param idMovie
     */
    public void deleteFromUser(Long idMovie) {
        UserInfo admin = userRepository.findByFirstname("adminI");
        Set<Movie> movies = admin.getMovies();
        Set<Movie> moviesNew = new HashSet<>();
        for (Movie m : movies) {
            if (!m.getId().equals(idMovie)) {
                moviesNew.add(m);
            }
        }
        admin.setMovies(moviesNew);
    }

    /**
     * FindAll From User
     *
     * @param user
     * @return
     */
    public Set<Movie> findAllFromUser() {
        return userRepository.findByFirstname("adminI").getMovies();
    }

    /**
     * getSortByPopularity
     *
     * @return
     */
    public List<Movie> getSortByPopularity() {
        List<Movie> albums = movieRepository.findAll();
        List<Movie> sortAlbums = new ArrayList<>();
        List<Movie> sortAlbums2 = new ArrayList<>();
        Map<Movie, Integer> map = new HashMap<>();
        for (Movie a : albums) {
            map.put(a, getNumberOfOwners(a));
        }
        map = sortByValue(map);

        for (Map.Entry entry : map.entrySet()) {
            sortAlbums.add((Movie) entry.getKey());
        }

        for (int i = sortAlbums.size() - 1; i >= 0; i--) {
            sortAlbums2.add(sortAlbums.get(i));
        }

        return sortAlbums2;
    }

    private static Map<Movie, Integer> sortByValue(Map<Movie, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<Movie, Integer>> list
                = new LinkedList<Map.Entry<Movie, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Movie, Integer>>() {
            public int compare(Map.Entry<Movie, Integer> o1,
                    Map.Entry<Movie, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Movie, Integer> sortedMap = new LinkedHashMap<Movie, Integer>();
        for (Map.Entry<Movie, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private int getNumberOfOwners(Movie album) {
        int numberOfOwner = 0;
        List<UserInfo> users = userRepository.findAll();
        for (UserInfo u : users) {
            for (Movie a : u.getMovies()) {
                if (a.equals(album)) {
                    numberOfOwner++;
                }
            }
        }
        return numberOfOwner;
    }
}
