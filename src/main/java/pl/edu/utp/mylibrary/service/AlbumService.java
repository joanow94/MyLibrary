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
import pl.edu.utp.mylibrary.model.Album;
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.repository.AlbumRepository;

/**
 *
 * @author jnowakowska
 */
@Service
@Transactional
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

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
    public void addAlbum(String title, String artist, String year, String genre) {
        albumRepository.save(new Album(Long.MIN_VALUE, title, artist, year, genre));
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
    public void addToUser(UserInfo user, Long idAlbum) {
        Album album = albumRepository.findOne(idAlbum);
        Set<Album> albums = user.getAlbums();
        albums.add(album);
        user.setAlbums(albums);
    }

    /**
     * TODO Delete Album From User
     *
     * @param user
     * @param idAlbum
     */
    public void deleteFromUser(UserInfo user, Long idAlbum) {
        Set<Album> albums = user.getAlbums();
        Set<Album> albumsNew = new HashSet<>();
        for (Album a : albums) {
            if (!a.getId().equals(idAlbum)) {
                albumsNew.add(a);
            }
        }
        user.setAlbums(albumsNew);
    }

    /**
     * FindAll From User
     *
     * @param user
     * @return
     */
    public Set<Album> findAllFromUser(UserInfo user) {
        return user.getAlbums();
    }
}
