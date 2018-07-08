package com.direb.utils;


import org.springframework.ui.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtraFunctional {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean checkingCredentials(String username, String password, String email, Model model){
        int uLen = username.length();
        int pLen = password.length();
        if (uLen >= 3 && uLen < 20 && pLen >= 4 && pLen < 20 && emailValidate(email)){
            return true;
        } else {
            model.addAttribute("message", "Invalid format of username/password/email! Try again");
            return false;
        }
    }

    private static boolean emailValidate(String emailStr){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }


}
