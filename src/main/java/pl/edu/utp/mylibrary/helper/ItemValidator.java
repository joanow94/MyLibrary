/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.helper;

import org.springframework.stereotype.Service;
import pl.edu.utp.mylibrary.enums.ErrorInfo;

/**
 *
 * @author nowakowska joanna
 */
@Service
public class ItemValidator {

    public String validateField(String stringField) {
        if (null == stringField || stringField.length() < 1) {
            return ErrorInfo.EMPTY_FIELD.toString();
        } else if (stringField.length() > 5) {
            return ErrorInfo.MAX_LENGTH.toString();
        } else {
            return "";
        }
    }

}
