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

    public String validateField(String stringField) {
        if (null == stringField || stringField.length() < 1) {
            return ErrorInfo.EMPTY_FIELD.getInfo();
        } else if (stringField.length() > 5) {
            return ErrorInfo.MAX_LENGTH.getInfo();
        } else {
            return null;
        }
    }

}
