/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.helper;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.mylibrary.enums.ErrorInfo;
import pl.edu.utp.mylibrary.model.UserInfo;
import pl.edu.utp.mylibrary.service.UserService;

/**
 *
 * @author nowakowska joanna
 */
public class RegisterValidator {

    @Autowired
    private UserService userService;

    public Boolean isCorrect(String firstname, String lastname, String login, String password, String confirmPassword) {
        Boolean isOk = true;
        if (null != validateStringField(firstname)) {
            isOk = false;
        }
        if (null != validateStringField(lastname)) {
            isOk = false;
        }
        if (null != validateStringField(login)) {
            isOk = false;
        }
        if (null != validateStringField(password)) {
            isOk = false;
        }
        if (null != validateUniqueLogin(login)) {
            isOk = false;
        }
        if (null != validatePasswords(password, confirmPassword)) {
            isOk = false;
        }
        return isOk;
    }

    public String validateStringField(String stringField) {
        if (null == stringField || stringField.length() < 1) {
            return ErrorInfo.EMPTY_FIELD.getInfo();
        } else if (stringField.length() > 20) {
            return ErrorInfo.MAX_LENGTH.getInfo();
        } else if (stringField.length() < 3) {
            return ErrorInfo.MIN_LENGTH.getInfo();
        } else {
            return null;
        }
    }

    public String validateUniqueLogin(String login) {
        for (UserInfo u : userService.findAll()) {
            if (u.getLogin().equals(login)) {
                return ErrorInfo.LOGIN_UNIQUE.getInfo();
            }
        }
        return null;
    }

    public String validatePasswords(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return ErrorInfo.PASSWORDS.getInfo();
        } else {
            return null;
        }
    }

}
