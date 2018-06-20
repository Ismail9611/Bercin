package com.direb.controller;


import com.direb.domain.Role;
import com.direb.domain.User;
import com.direb.repository.UserRepository;
import com.direb.utils.ExtraFunctional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String registration(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("password_rep") String password_rep,
                               Model model) {
        if (ExtraFunctional.checkingCredentials(username, password, model)) {
            if(password.equals(password_rep)) {
                User userFromDb = userRepository.findByUsername(username);
                if (userFromDb != null) {
                    model.addAttribute("message", "User with username '" + userFromDb.getUsername() + "' is already exists!");
                    return "registration";
                }
                User user = new User(username, password);
                user.setRoles(Collections.singleton(Role.USER));
                userRepository.save(user);
                return "index";
            } else {
                model.addAttribute("message", "Passwords do not match!");
            }
        }
        return "registration";
    }


}
