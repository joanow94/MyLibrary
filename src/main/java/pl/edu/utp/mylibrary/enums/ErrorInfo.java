/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.enums;

/**
 *
 * @author nowakowska joanna
 */
public enum ErrorInfo {
    LOGIN_PASSWORD("Niepoprawny login lub hasło!"),
    MAX_LENGTH("Przekroczono dozwoloną ilość znaków!"),
    MIN_LENGTH("Za mała ilość znaków!"),
    PASSWORDS("Hasła różnią się!"),
    LOGIN_UNIQUE("Login jest zajęty!"),
    EMPTY_FIELD("Pole wymagane jest puste!");

    private String info;

    private ErrorInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
