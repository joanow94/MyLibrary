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
    LOGIN("Niepoprawny login lub hasło!"),
    MAX_LENGTH("Przekroczono dozwoloną ilość znaków");

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
