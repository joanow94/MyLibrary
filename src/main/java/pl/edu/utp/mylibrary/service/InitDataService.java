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
            new Book(null, "Antygona", "Sofokles", "Grek"),
            new Book(null, "Mitologia", "Jan Parandowski", "WSiP"),
            new Book(null, "Quo vadis", "Henryk Sienkiewicz", "Albatros"),
            new Book(null, "Ogniem i mieczem", "Henryk Sienkiewicz", "Grek"),
            new Book(null, "Potop", "Henryk Sienkiewicz", "Grek"),
            new Book(null, "Pan Wołodyjowski", "Henryk Sienkiewicz", "HK"),
            new Book(null, "Makbet", "William Szekspir", "Olimp"),
            new Book(null, "Świętoszek", "Molier", "Bajka"),
            new Book(null, "Faust", "Johann Goethe", "WSiP"),
            new Book(null, "Konrad Wallenrod", "Adam Mickiewicz", "Grek"),
            new Book(null, "Dziady", "Adam Mickiewicz", "Amber"),
            new Book(null, "Pan Tadeusz", "Adam Mickiewicz", "Amber"),
            new Book(null, "Kordian", "Juliusz Słowacki", "Arkady"),
            new Book(null, "Nie-Boska Komedia", "Zygmunt Krasiński", "Grek"),
            new Book(null, "Nad Niemnem", "Eliza Orzeszkowa", "Bis"),
            new Book(null, "Lalka", "Bolesław Prus", "Czarna owca"),
            new Book(null, "Zbrodnia i kara", "Fiodor Dostojewski", "Mediasat Poland"),
            new Book(null, "Chłopi", "Władysław Reymont", "Bis"),
            new Book(null, "Ludzie bezdomni", "Stefan Żeromski", "BOOK"),
            new Book(null, "Noc listopadowa", "Stanisław Wyspiański", "GWP"),
            new Book(null, "Wesele", "Stanisław Wyspiański", "HiN"),
            new Book(null, "Wyzwolenie", "Stanisław Wyspiański", "Albatros"),
            new Book(null, "Granica", "Zofia Nałkowska", "NewAge"),
            new Book(null, "Ferdydurke", "Witold Gombrowicz", "NewAge"),
            new Book(null, "Medaliony", " Zofia Nałkowska", "Bis"),
            new Book(null, "Inny świat", "Gustaw Herling-Grudziński", "Wydawnictwo Literackie"),
            new Book(null, "Zdążyć przed Panem Bogiem", "Hanna Krall", "Iskry"),
            new Book(null, "My, dzieci z dworca ZOO", "Christiane Felscherinow", "HK"),
            new Book(null, "Starcie królów", "George R.R. Martin ", "Iskry"),
            new Book(null, "Nawałnica mieczy #1: Stal i śnieg", "George R.R. Martin ", "Arkady"),
            new Book(null, "Pan Lodowego Ogrodu - tom I", " Jarosław Grzędowicz ", "WWZ"),
            new Book(null, "Zieleń szmaragdu", "Kerstin Gier", "WWZ"),
            new Book(null, "Nawałnica mieczy #2: Krew i złoto", "George R.R. Martin", "Mul"),
            new Book(null, "Mechaniczny anioł", "Cassandra Clare", "Wspaniałe Wydawnicctwo"),
            new Book(null, "Mechaniczna księżniczka", "Cassandra Clare", "Wspaniałe Wydawnicctwo"),
            new Book(null, "Ojciec Chrzestny", "Mario Puzo", "Pure"),
            new Book(null, "Droga królów", "Brandon Sanderson", "Saga"),
            new Book(null, "Mężczyźni, którzy nienawidzą kobiet", "Stieg Larsson", "Saga"),
            new Book(null, "Mechaniczny książę", "Cassandra Clare", "Hans"),
            new Book(null, "Pan Lodowego Ogrodu - tom III", "Jarosław Grzędowicz", "WWZ"),
            new Book(null, "Zielona Mila", "Stephen King", "Pure"),
            new Book(null, "Harry Potter i Insygnia Śmierci", "Joanne Kathleen Rowling ", "Media Rodzina"),
            new Book(null, "Był sobie pies", "W. Bruce Cameron ", "Nadzieja"),
            new Book(null, "Igrzyska Śmierci. Trylogia", "Suzanne Collins ", "Mul"),
            new Book(null, "Dziwne losy Jane Eyre", "Charlotte Brontë", "Mul"),
            new Book(null, "Władca Pierścieni", "John Ronald Reuel Tolkien", "Amber"),
            new Book(null, "Dziewczyna, która igrała z ogniem", "Stieg Larsson", "WWZ"),
            new Book(null, "Harry Potter i Kamień Filozoficzny", "Joanne Kathleen Rowling", "Media Rodzina"),
            new Book(null, "Księga wszystkich dokonań Sherlocka Holmesa", "Arthur Conan Doyle", "Hans"),
            new Book(null, "Harry Potter i Komnata Tajemnic", "Joanne Kathleen Rowling", "Media Rodzina"),
            new Book(null, "Harry Potter i więzień Azkabanu", "Joanne Kathleen Rowling", "Media Rodzina"),
            new Book(null, "Harry Potter i Czara Ognia", "Joanne Kathleen Rowling", "Media Rodzina"),
            new Book(null, "Harry Potter i Książę Półkrwi", "Joanne Kathleen Rowling", "Media Rodzina"),
            new Book(null, "Harry Potter i Zakon Feniksa", "Joanne Kathleen Rowling", "Media Rodzina"),
            new Book(null, "Wiedźmin. Ostatnie życzenie", "Andrzej Sapkowski", "superNOWA"),
            new Book(null, "Wiedźmin. Miecz przeznaczenia", "Andrzej Sapkowski", "superNOWA")
    );

    List<Album> albums = Arrays.asList(
            new Album(null, "Kill ’Em All", "Metalica", "1987", "rock"),
            new Album(null, "Ride the Lightning", "Metalica", "1987", "rock"),
            new Album(null, "Bumerang", "Kortez", "2016", "alternative/indie"),
            new Album(null, "3", "Domowe melodie", "2015", "alternative/indie"),
            new Album(null, "Domowe melodie", "Domowe melodie", "2014", "alternative/indie"),
            new Album(null, "Matka I Zony", "Mikromusic", "2010", "alternative/indie"),
            new Album(null, "Witness", "Katy Perry", "2016", "pop"),
            new Album(null, "Ti Amo", "Phonix", "2000", "pop"),
            new Album(null, "Monkey Business", "Margaret", "2017", "pop"),
            new Album(null, "Higher Truth", "Chris Cornell", "1999", "rock"),
            new Album(null, "Iron Man 2", "AC/DC", "1994", "rock"),
            new Album(null, "Telefon", "Pablopavo", "2000", "regge"),
            new Album(null, "Be Here Now", "Oasis", "1997", "rock"),
            new Album(null, "Foo Fighters", "Foo Fighters", "1991", "metal"),
            new Album(null, "Nevermind", "Nirvana", "1990", "grange"),
            new Album(null, "In Utero", "Nirvana", "1978", "grange"),
            new Album(null, "Led Zeppelin IV", "Led Zeppelin", "1920", "metal"),
            new Album(null, "Bleach", "Nirvana", "1999", "grange"),
            new Album(null, "Na szczycie", "Jamal", "2004", "regge"),
            new Album(null, "Marmur", "Taco Hemingway", "2015", "rap"),
            new Album(null, "Wiatr", "Taco Hemingway", "2014", "rap"),
            new Album(null, "Mów Mi Dobrze", "Happysad", "2008", "rock")
    );
    List<Movie> movies = Arrays.asList(
            new Movie(null, "Zielona mila", "Frank Darabont", "1999", "USA", "dramat"),
            new Movie(null, "Titanic", "James Cameron", "1997", "USA", "dramat"),
            new Movie(null, "Avatar", "James Cameron", "2009", "USA", "akcja"),
            new Movie(null, "Obcy", "James Cameron", "1989", "USA", "Horror"),
            new Movie(null, "Milczenie owiec", "Jonathan Demme", "1999", "Niemcy", "horror"),
            new Movie(null, "Filadelfia", "Jonathan Demme", "1998", "Anglia", "dramat"),
            new Movie(null, "Full Metal Jacket", "Stanley Kubrick", "1978", "Anglia", "sci-fi"),
            new Movie(null, "Mechaniczna pomarańcza", "Stanley Kubrick", "1987", "USA", "sci-fi"),
            new Movie(null, "Lśnienie", "Stanley Kubrick", "1991", "USA", "horror"),
            new Movie(null, "Pianista", "Roman Polański", "2002", "Polska", "wojenny"),
            new Movie(null, "Park Jurajski", "Steven Spielberg", "2014", "USA", "sci-fi"),
            new Movie(null, "Lista Shindlera", "Steven Spielberg", "1997", "Anglia", "dramat"),
            new Movie(null, "Pulp Fiction", "Quentin Tarantino", "1994", "Niemcy", "komedia"),
            new Movie(null, "Django", "Quentin Tarantino", "2016", "USA", "komedia"),
            new Movie(null, "Bękarty wojny", "Quentin Tarantino", "2010", "Anglia", "wojenny"),
            new Movie(null, "Nietykalni", "Olivier Nakache", "2011", "Francja", "komedia"),
            new Movie(null, "Lot nad kukułczym gniazdem", "Milos Forman", "1979", "Anglia", "dramat"),
            new Movie(null, "Forrest Gump", "Robert Zemeckis", "2000", "USA", "komedia"),
            new Movie(null, "Powrót do przeszłości", "Robert Zemeckis", "1998", "USA", "przygodowy"),
            new Movie(null, "Kontakt", "Robert Zemeckis", "1997", "USA", "sci-fi")
    );

}
