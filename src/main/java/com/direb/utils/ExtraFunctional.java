package com.direb.utils;


import org.springframework.ui.Model;

public class ExtraFunctional {


    public static boolean checkingCredentials(String username, String password, Model model){
        int uLen = username.length();
        int pLen = password.length();
        if (uLen >= 3 && uLen < 20 && pLen >= 4 && pLen < 20){
            return true;
        } else {
            model.addAttribute("message", "Invalid format of username/password! Try again");
            return false;
        }
    }
}
