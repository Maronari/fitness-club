package ru.mirea.app.fitness_club.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FitnessController {

    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    public String profile(Model model) {
        return "html/profile";
    }

}
