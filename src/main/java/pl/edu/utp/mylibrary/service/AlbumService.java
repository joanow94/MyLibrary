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
import pl.edu.utp.mylibrary.model.Album;
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.repository.AlbumRepository;
import pl.edu.utp.mylibrary.repository.UserRepository;

/**
 *
 * @author jnowakowska
 */
@Service
@Transactional
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * FindAll Albums
     *
     * @return
     */
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    /**
     * Add Album
     *
     * @param title
     * @param artist
     * @param year
     * @param genre
     */
    public void addAlbum(Album album) {
        albumRepository.save(album);
    }

    /**
     * Delete Album
     *
     * @param id
     */
    public void deleteAlbum(Long id) {
        albumRepository.delete(id);
    }

    /**
     * Find Album By Search Term
     *
     * @param searchTerm
     * @return
     */
    public List<Album> findBySearchTerm(String searchTerm) {
        return albumRepository.findBySearchTerm(searchTerm);
    }

    /**
     * TODO Add Album To User
     *
     * @param user
     * @param idAlbum
     */
    public void addToUser(Album album) {
        UserInfo admin = userRepository.findByFirstname("adminI");
        Set<Album> albums = admin.getAlbums();
        albums.add(album);
        admin.setAlbums(albums);
    }
    
    public void addToUser(Long idAlbum) {
        UserInfo admin = userRepository.findByFirstname("adminI");
        Album album = albumRepository.findOne(idAlbum);
        Set<Album> albums = admin.getAlbums();
        albums.add(album);
        admin.setAlbums(albums);
    }

    /**
     * TODO Delete Album From User
     *
     * @param user
     * @param idAlbum
     */
    public void deleteFromUser(Long idAlbum) {
        UserInfo admin = userRepository.findByFirstname("adminI");
        Set<Album> albums = admin.getAlbums();
        Set<Album> albumsNew = new HashSet<>();
        for (Album a : albums) {
            if (!a.getId().equals(idAlbum)) {
                albumsNew.add(a);
            }
        }
        admin.setAlbums(albumsNew);
    }

    /**
     * FindAll From User
     *
     * @return
     */
    public Set<Album> findAllFromUser() {
        return userRepository.findByFirstname("adminI").getAlbums();
    }
    
    /**
     * getSortByPopularity
     *
     * @return
     */
    public List<Album> getSortByPopularity() {
        List<Album> albums = albumRepository.findAll();
        List<Album> sortAlbums = new ArrayList<>();
        List<Album> sortAlbums2 = new ArrayList<>();
        Map<Album, Integer> map = new HashMap<>();
        for (Album a : albums) {
            map.put(a, getNumberOfOwners(a));
        }
        map = sortByValue(map);

        for (Map.Entry entry : map.entrySet()) {
            sortAlbums.add((Album) entry.getKey());
        }

        for (int i = sortAlbums.size() - 1; i >= 0; i--) {
            sortAlbums2.add(sortAlbums.get(i));
        }

        return sortAlbums2;
    }

    private static Map<Album, Integer> sortByValue(Map<Album, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<Album, Integer>> list
                = new LinkedList<Map.Entry<Album, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Album, Integer>>() {
            public int compare(Map.Entry<Album, Integer> o1,
                    Map.Entry<Album, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Album, Integer> sortedMap = new LinkedHashMap<Album, Integer>();
        for (Map.Entry<Album, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private int getNumberOfOwners(Album album) {
        int numberOfOwner = 0;
        List<UserInfo> users = userRepository.findAll();
        for (UserInfo u : users) {
            for (Album a : u.getAlbums()) {
                if (a.equals(album)) {
                    numberOfOwner++;
                }
            }
        }
        return numberOfOwner;
    }
}
