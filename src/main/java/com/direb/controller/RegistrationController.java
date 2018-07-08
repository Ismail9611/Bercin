package com.direb.controller;


import com.direb.domain.User;
import com.direb.repository.UserRepository;
import com.direb.service.UserService;
import com.direb.utils.ExtraFunctional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String registration(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("password_rep") String password_rep,
                               Model model) {
        if (ExtraFunctional.checkingCredentials(username, password, email, model)) {
            if (password.equals(password_rep)) {
                User user = new User(username, email, password);
                if (!userService.addUser(user)) {
                    model.addAttribute("message", "Failed while signing in, try again!");
                    return "registration";
                }
            } else {
                model.addAttribute("message", "Passwords do not match!");
                return "registration";
            }
        }
        return "registration";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "Your account successfully activated!");
            return "/login";
        } else {
            model.addAttribute("message", "Failed while activating account!");
            return "/registration";
        }

    }


}
