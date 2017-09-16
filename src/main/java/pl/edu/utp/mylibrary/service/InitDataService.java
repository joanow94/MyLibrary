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
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.repository.AlbumRepository;
import pl.edu.utp.mylibrary.repository.BookRepository;
import pl.edu.utp.mylibrary.repository.MovieRepository;
import pl.edu.utp.mylibrary.repository.UserRepository;

/**
 *
 * @author jnowakowska
 */
@Service
@Transactional
public class InitDataService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(InitDataService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private MovieRepository movieRepository;

    Random random = new Random();
    List<UserInfo> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        users = generatePersons(20);
        bookRepository.save(books);
        albumRepository.save(albums);
        movieRepository.save(movies);

        for (UserInfo p : users) {
            p.setBooks(randomBooks());
            p.setAlbums(randomAlbums());
            p.setMovies(randomMovies());
        }
        UserInfo admin = new UserInfo(Long.MIN_VALUE, "adminI", "adminN", "admin", "x");
        admin.setBooks(randomBooks());
        admin.setAlbums(randomAlbums());
        LOGGER.info("Albumy admina: " + admin.getAlbums().toString());
        admin.setMovies(randomMovies());
        users.add(admin);
        userRepository.save(users);
    }

    public List<UserInfo> generatePersons(int numberOfPersons) {
        for (int i = 0; i < numberOfPersons; i++) {

            String firstname = names.get(random.nextInt(names.size()));
            String lastname = surnames.get(random.nextInt(surnames.size()));

            users.add(new UserInfo(Long.MIN_VALUE, firstname, lastname, genarateLogin(firstname, lastname), firstname + lastname));
        }
        return users;
    }

    private String genarateLogin(String firstname, String lastname) {
        String login;
        do {
            login = firstname.toLowerCase() + lastname.toLowerCase() + random.nextInt(100);
        } while (!isUnique(login));

        return login;
    }

    private Boolean isUnique(String login) {
        for (UserInfo u : userRepository.findAll()) {
            if (u.getLogin().equals(login)) {
                return false;
            }
        }
        return true;
    }

    private Set<Book> randomBooks() {
        Set<Book> booksForUser = new HashSet<>();
        int numOfBooks = random.nextInt(30);
        for (int i = 0; i < numOfBooks; i++) {
            booksForUser.add(bookRepository.findAll().get(random.nextInt(books.size())));
        }
        return booksForUser;
    }

    private Set<Album> randomAlbums() {
        Set<Album> albumsForUser = new HashSet<>();
        int numOfAlbums = random.nextInt(30);
        for (int i = 0; i < numOfAlbums; i++) {
            albumsForUser.add(albumRepository.findAll().get(random.nextInt(albums.size())));
        }
        return albumsForUser;
    }

    private Set<Movie> randomMovies() {
        Set<Movie> moviesForUser = new HashSet<>();
        int numOfMovies = random.nextInt(30);
        for (int i = 0; i < numOfMovies; i++) {
            moviesForUser.add(movieRepository.findAll().get(random.nextInt(movies.size())));
        }
        return moviesForUser;
    }

    List<String> names = Arrays.asList("Hanna", "Mateusz", "Magdalena", "Daniel", "Łukasz", "Marcin", "Marta", "Dorota",
            "Karolina", "Błażej", "Tomasz", "Damian", "Grzegorz", "Piotr", "Konrad", "Barbara", "Natalia", "Michał",
            "Danuta", "Anna", "Jan");
    List<String> surnames = Arrays.asList("Papis", "Wiadro", "Poredo", "Nowak", "Goszka", "Gutek", "Gusciora", "Wójcik",
            "Kowalczyk", "Woźniak", "Mazur", "Krawczyk", "Kaczmarek", "Zając", "Król", "Stępień", "Adamczyk", "Dudek",
            "Pawlak", "Walczak", "Sikora");

    List<Book> books = Arrays.asList(
            new Book(Long.MIN_VALUE, "Antygona", "Sofokles", "wyd2"),
            new Book(Long.MIN_VALUE, "Mitologia", "Jan Parandowski", "wyd2"),
            new Book(Long.MIN_VALUE, "Quo vadis", "Henryk Sienkiewicz", "wyd2"),
            new Book(Long.MIN_VALUE, "Ogniem i mieczem", "Henryk Sienkiewicz", "wyd3"),
            new Book(Long.MIN_VALUE, "Potop", "Henryk Sienkiewicz", ""),
            new Book(Long.MIN_VALUE, "Pan Wołodyjowski", "Henryk Sienkiewicz", ""),
            new Book(Long.MIN_VALUE, "Makbet", "William Szekspir", ""),
            new Book(Long.MIN_VALUE, "Świętoszek", "Molier", ""),
            new Book(Long.MIN_VALUE, "Faust", "Johann Goethe", ""),
            new Book(Long.MIN_VALUE, "Konrad Wallenrod", "Adam Mickiewicz", ""),
            new Book(Long.MIN_VALUE, "Dziady", "Adam Mickiewicz", ""),
            new Book(Long.MIN_VALUE, "Pan Tadeusz", "Adam Mickiewicz", ""),
            new Book(Long.MIN_VALUE, "Kordian", "Juliusz Słowacki", ""),
            new Book(Long.MIN_VALUE, "Nie-Boska Komedia", "Zygmunt Krasiński", ""),
            new Book(Long.MIN_VALUE, "Nad Niemnem", "Eliza Orzeszkowa", ""),
            new Book(Long.MIN_VALUE, "Lalka", "Bolesław Prus", ""),
            new Book(Long.MIN_VALUE, "Zbrodnia i kara", "Fiodor Dostojewski", "Mediasat Poland"),
            new Book(Long.MIN_VALUE, "Chłopi", "Władysław Reymont", ""),
            new Book(Long.MIN_VALUE, "Ludzie bezdomni", "Stefan Żeromski", ""),
            new Book(Long.MIN_VALUE, "Noc listopadowa", "Stanisław Wyspiański", ""),
            new Book(Long.MIN_VALUE, "Wesele", "Stanisław Wyspiański", ""),
            new Book(Long.MIN_VALUE, "Wyzwolenie", "Stanisław Wyspiański", ""),
            new Book(Long.MIN_VALUE, "Granica", "Zofia Nałkowska", ""),
            new Book(Long.MIN_VALUE, "Ferdydurke", "Witold Gombrowicz", ""),
            new Book(Long.MIN_VALUE, "Medaliony", " Zofia Nałkowska", ""),
            new Book(Long.MIN_VALUE, "Inny świat", "Gustaw Herling-Grudziński", ""),
            new Book(Long.MIN_VALUE, "Zdążyć przed Panem Bogiem", "Hanna Krall", ""),
            new Book(Long.MIN_VALUE, "My, dzieci z dworca ZOO", "Christiane Felscherinow", ""),
            new Book(Long.MIN_VALUE, "Starcie królów", "George R.R. Martin ", ""),
            new Book(Long.MIN_VALUE, "Nawałnica mieczy #1: Stal i śnieg", "George R.R. Martin ", ""),
            new Book(Long.MIN_VALUE, "Pan Lodowego Ogrodu - tom I", " Jarosław Grzędowicz ", ""),
            new Book(Long.MIN_VALUE, "Zieleń szmaragdu", "Kerstin Gier", ""),
            new Book(Long.MIN_VALUE, "Nawałnica mieczy #2: Krew i złoto", "George R.R. Martin", ""),
            new Book(Long.MIN_VALUE, "Mechaniczny anioł", "Cassandra Clare", ""),
            new Book(Long.MIN_VALUE, "Mechaniczna księżniczka", "Cassandra Clare", ""),
            new Book(Long.MIN_VALUE, "Ojciec Chrzestny", "Mario Puzo", ""),
            new Book(Long.MIN_VALUE, "Droga królów", "Brandon Sanderson", ""),
            new Book(Long.MIN_VALUE, "Mężczyźni, którzy nienawidzą kobiet", "Stieg Larsson", "wyd2"),
            new Book(Long.MIN_VALUE, "Mechaniczny książę", "Cassandra Clare", ""),
            new Book(Long.MIN_VALUE, "Pan Lodowego Ogrodu - tom III", "Jarosław Grzędowicz", ""),
            new Book(Long.MIN_VALUE, "Zielona Mila", "Stephen King", ""),
            new Book(Long.MIN_VALUE, "Harry Potter i Insygnia Śmierci", "Joanne Kathleen Rowling ", "Media Rodzina"),
            new Book(Long.MIN_VALUE, "Był sobie pies", "W. Bruce Cameron ", ""),
            new Book(Long.MIN_VALUE, "Igrzyska Śmierci. Trylogia", "Suzanne Collins ", ""),
            new Book(Long.MIN_VALUE, "Dziwne losy Jane Eyre", "Charlotte Brontë", ""),
            new Book(Long.MIN_VALUE, "Władca Pierścieni", "John Ronald Reuel Tolkien", "Amber"),
            new Book(Long.MIN_VALUE, "Dziewczyna, która igrała z ogniem", "Stieg Larsson", ""),
            new Book(Long.MIN_VALUE, "Harry Potter i Kamień Filozoficzny", "Joanne Kathleen Rowling", "Media Rodzina"),
            new Book(Long.MIN_VALUE, "Księga wszystkich dokonań Sherlocka Holmesa", "Arthur Conan Doyle", ""),
            new Book(Long.MIN_VALUE, "Harry Potter i Komnata Tajemnic", "Joanne Kathleen Rowling", "Media Rodzina"),
            new Book(Long.MIN_VALUE, "Harry Potter i więzień Azkabanu", "Joanne Kathleen Rowling", "Media Rodzina"),
            new Book(Long.MIN_VALUE, "Harry Potter i Czara Ognia", "Joanne Kathleen Rowling", "Media Rodzina"),
            new Book(Long.MIN_VALUE, "Harry Potter i Książę Półkrwi", "Joanne Kathleen Rowling", "Media Rodzina"),
            new Book(Long.MIN_VALUE, "Harry Potter i Zakon Feniksa", "Joanne Kathleen Rowling", "Media Rodzina"),
            new Book(Long.MIN_VALUE, "Wiedźmin. Ostatnie życzenie", "Andrzej Sapkowski", "superNOWA"),
            new Book(Long.MIN_VALUE, "Wiedźmin. Miecz przeznaczenia", "Andrzej Sapkowski", "superNOWA")
    );
    List<Album> albums = Arrays.asList(
            new Album(Long.MIN_VALUE, "tytul1", "zespol1", "1994", "gat"),
            new Album(Long.MIN_VALUE, "tytul2", "zespol1", "1994", "gat"),
            new Album(Long.MIN_VALUE, "tytul3", "zespol1", "1994", "gat"),
            new Album(Long.MIN_VALUE, "tytul4", "zespol1", "1994", "gat")
    );
    List<Movie> movies = Arrays.asList(
            new Movie(Long.MIN_VALUE, "tytul1", "rezyse1", "2000", "US", "gat"),
            new Movie(Long.MIN_VALUE, "tytul2", "rezyse1", "2000", "US", "gat"),
            new Movie(Long.MIN_VALUE, "tytul3", "rezyse1", "2000", "US", "gat"),
            new Movie(Long.MIN_VALUE, "tytul4", "rezyse1", "2000", "US", "gat"),
            new Movie(Long.MIN_VALUE, "tytul5", "rezyse1", "2000", "US", "gat")
    );
}
