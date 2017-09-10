/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.helper;

import pl.edu.utp.mylibrary.enums.ErrorInfo;

/**
 *
 * @author nowakowska joanna
 */
public class ItemValidator {

    public Boolean isCorrectAlbum(String title, String artist, String year, String genre) {
        return (null == validateField(title) && null == validateField(artist) && null == validateField(year) && null == validateField(genre)) ? true : false;
    }

    public Boolean isCorrectBook(String title, String author, String publisher) {
        return (null == validateField(title) && null == validateField(author) && null == validateField(publisher)) ? true : false;
    }

    public Boolean isCorrectMovie(String title, String director, String year, String country, String genre) {
        return (null == validateField(title) && null == validateField(director) && null == validateField(year) && null == validateField(country) && null == validateField(genre)) ? true : false;
    }

    public String validateField(String stringField) {
        if (null == stringField || stringField.length() < 1) {
            return ErrorInfo.EMPTY_FIELD.getInfo();
        } else if (stringField.length() > 50) {
            return ErrorInfo.MAX_LENGTH.getInfo();
        } else {
            return null;
        }
    }

}
