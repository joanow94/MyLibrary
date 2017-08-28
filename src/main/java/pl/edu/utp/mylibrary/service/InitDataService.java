/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.utp.mylibrary.model.Album;
import pl.edu.utp.mylibrary.model.Book;
import pl.edu.utp.mylibrary.model.Movie;
import pl.edu.utp.mylibrary.model.Person;
import pl.edu.utp.mylibrary.repository.AlbumRepository;
import pl.edu.utp.mylibrary.repository.BookRepository;
import pl.edu.utp.mylibrary.repository.MovieRepository;
import pl.edu.utp.mylibrary.repository.PersonRepository;

/**
 *
 * @author jnowakowska
 */
@Service
@Transactional
public class InitDataService {
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(InitDataService.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private MovieRepository movieRepository;

    Random random = new Random();
    List<Person> persons = new ArrayList<>();

    @PostConstruct
    public void init() {
        persons = generatePersons(10);
        bookRepository.save(books);
        albumRepository.save(albums);
        movieRepository.save(movies);

        for (Person p : persons) {
            LOGGER.info("Człowiek : " + p.toString());
            p.setBooks(randomBooks());
            p.setAlbums(randomAlbums());
            p.setMovies(randomMovies());
        }
        personRepository.save(persons);
    }

    
    public List<Person> generatePersons(int numberOfPersons) {
        for (int i = 0; i < numberOfPersons; i++) {

            String firstname = names.get(random.nextInt(names.size()));
            String lastname = surnames.get(random.nextInt(surnames.size()));

            persons.add(new Person(Long.MIN_VALUE, firstname, lastname, genarateLogin(firstname, lastname), firstname + lastname));
        }
        return persons;
    }

    private String genarateLogin(String firstname, String lastname) {
        String login;
        do {
            login = firstname + lastname + random.nextInt(100);
        } while (!isUnique(login));

        return login;
    }

    private Boolean isUnique(String login) {
        Boolean isUnique = true;
        for (Person p : personRepository.findAll()) {
            if (p.getLogin().equals(login)) {
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }

    private Set<Book> randomBooks() {
        Set<Book> booksForPerson = new HashSet<>();
        for (int i = 0; i < random.nextInt(30); i++) {
            booksForPerson.add(bookRepository.findAll().get(random.nextInt(books.size())));
        }
        return booksForPerson;
    }

    private Set<Album> randomAlbums() {
        Set<Album> albumsForPerson = new HashSet<>();
        for (int i = 0; i < random.nextInt(30); i++) {
            albumsForPerson.add(albumRepository.findAll().get(random.nextInt(albums.size())));
        }
        return albumsForPerson;
    }

    private Set<Movie> randomMovies() {
        Set<Movie> moviesForPerson = new HashSet<>();
        for (int i = 0; i < random.nextInt(30); i++) {
            moviesForPerson.add(movieRepository.findAll().get(random.nextInt(movies.size())));
        }
        return moviesForPerson;
    }

    List<String> names = Arrays.asList("imię1", "imię2");
    List<String> surnames = Arrays.asList("nazwisko1", "nazwisko2");

    List<Book> books = Arrays.asList(
            new Book(Long.MIN_VALUE, "tytul1", "autor1", "wyda1"),
            new Book(Long.MIN_VALUE, "tutul2", "autor2", "wyd2"),
            new Book(Long.MIN_VALUE, "tytul3", "autor3", "wyd3")
    );
    List<Album> albums = Arrays.asList(
            new Album(Long.MIN_VALUE, "tytul1", "wyk1", "2000", "gat1"),
            new Album(Long.MIN_VALUE, "tytul2", "wyk2", "2000", "gat2")
    );
    List<Movie> movies = Arrays.asList(
            new Movie(Long.MIN_VALUE, "tytul1", "rezyse1", "2000", "pL", "dram"),
            new Movie(Long.MIN_VALUE, "tytul2", "rezy2", "2000", "pl", "dram")
    );
}
