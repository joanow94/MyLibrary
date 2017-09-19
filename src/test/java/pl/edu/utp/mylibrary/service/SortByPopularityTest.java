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
import pl.edu.utp.mylibrary.model.Book;
import pl.edu.utp.mylibrary.model.UserInfo;

/**
 *
 * @author nowakowska joanna
 */
public class SortByPopularityTest {

    public static List<Book> allBooks = new ArrayList<>();
    public static List<UserInfo> allUsers = new ArrayList<>();

//    public static void main(String[] args) {
//
//        Book book1 = new Book(Long.MIN_VALUE, "tytul1", "autor1", "publi1");
//        Book book2 = new Book(Long.MIN_VALUE, "tytul2", "autor1", "publi1");
//        Book book3 = new Book(Long.MIN_VALUE, "tytul3", "autor1", "publi1");
//        Book book4 = new Book(Long.MIN_VALUE, "tytul4", "autor1", "publi1");
//
//        allBooks.add(book1);
//        allBooks.add(book2);
//        allBooks.add(book3);
//        allBooks.add(book4);
//
//        UserInfo user1 = new UserInfo(Long.MIN_VALUE, "imie1", "nazwisko1", "login1", "pass1");
//        UserInfo user2 = new UserInfo(Long.MIN_VALUE, "imie2", "nazwisko2", "login1", "pass1");
//        UserInfo user3 = new UserInfo(Long.MIN_VALUE, "imie3", "nazwisko3", "login1", "pass1");
//        UserInfo user4 = new UserInfo(Long.MIN_VALUE, "imie4", "nazwisko4", "login1", "pass1");
//
//        Set<Book> s1 = new HashSet<>();
//        Set<Book> s2 = new HashSet<>();
//        Set<Book> s3 = new HashSet<>();
//        Set<Book> s4 = new HashSet<>();
//
//        s1.add(book1);
//        s1.add(book2);
//        s1.add(book3);
//        s1.add(book4);
//
//        s2.add(book2);
//        s2.add(book3);
//        s2.add(book4);
//
//        s3.add(book2);
//        s3.add(book3);
//        s3.add(book4);
//
//        s4.add(book2);
//
//        user1.setBooks(s1);
//        user2.setBooks(s2);
//        user3.setBooks(s3);
//        user4.setBooks(s4);
//
//        allUsers.add(user1);
//        allUsers.add(user2);
//        allUsers.add(user3);
//        allUsers.add(user4);
//
//        List<Book> mostPopular = findMostPopular();
//
//        for (Book b : mostPopular) {
//            System.out.println("Kasiazka" + b.getTitle());
//        }
//
//    }
    public static List<Book> findMostPopular() {
        List<Book> books = allBooks;
        List<Book> sortBooks = new ArrayList<>();
        List<Book> sortBooks2 = new ArrayList<>();
        Map<Book, Integer> map = new HashMap<>();
        for (Book b : books) {
            map.put(b, getNumberOfOwners(b));
        }
        map = sortByValue(map);

        for (Map.Entry entry : map.entrySet()) {
            sortBooks.add((Book) entry.getKey());
        }

        for (int i = sortBooks.size() - 1; i >= 0; i--) {
            sortBooks2.add(sortBooks.get(i));
        }

        return sortBooks2;
    }

    private static Map<Book, Integer> sortByValue(Map<Book, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<Book, Integer>> list
                = new LinkedList<Map.Entry<Book, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Book, Integer>>() {
            public int compare(Map.Entry<Book, Integer> o1,
                    Map.Entry<Book, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Book, Integer> sortedMap = new LinkedHashMap<Book, Integer>();
        for (Map.Entry<Book, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/
        return sortedMap;
    }

    private static int getNumberOfOwners(Book book) {
        int numberOfOwner = 0;
        List<UserInfo> users = allUsers;
        for (UserInfo u : users) {
            for (Book b : u.getBooks()) {
                if (b.equals(book)) {
                    numberOfOwner++;
                }
            }
        }
        return numberOfOwner;
    }
}
