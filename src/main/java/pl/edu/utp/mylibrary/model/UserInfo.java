/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author nowakowska joanna
 */
@Entity
public class UserInfo implements Serializable {

    /**
     * Fields
     */
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERINFO_BOOK",
            joinColumns = {
                @JoinColumn(name = "USERINFO_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "BOOK_ID")})
    private Set<Book> books = new HashSet<>();

    @Column(nullable = true)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERINFO_ALBUM",
            joinColumns = {
                @JoinColumn(name = "USERINFO_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "ALBUM_ID")})
    private Set<Album> albums = new HashSet<>();

    @Column(nullable = true)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERINFO_MOVIE",
            joinColumns = {
                @JoinColumn(name = "USERINFO_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "MOVIE_ID")})
    private Set<Movie> movies = new HashSet<>();

    /**
     * Constructors
     */
    public UserInfo() {
    }

    public UserInfo(Long id, String firstname, String lastname, String login, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

    public UserInfo(Long id, String firstname, String lastname, String login, String password, Set<Book> books, Set<Album> albums, Set<Movie> movies) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.books = books;
        this.albums = albums;
        this.movies = movies;
    }

    /**
     * Gettrs and Setters
     */
    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Euals and HashCode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.firstname);
        hash = 97 * hash + Objects.hashCode(this.lastname);
        hash = 97 * hash + Objects.hashCode(this.login);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Objects.hashCode(this.books);
        hash = 97 * hash + Objects.hashCode(this.albums);
        hash = 97 * hash + Objects.hashCode(this.movies);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserInfo other = (UserInfo) obj;
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.books, other.books)) {
            return false;
        }
        if (!Objects.equals(this.albums, other.albums)) {
            return false;
        }
        if (!Objects.equals(this.movies, other.movies)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", login=" + login + ", password=" + password + ", books=" + books + ", albums=" + albums + ", movies=" + movies + '}';
    }
}
