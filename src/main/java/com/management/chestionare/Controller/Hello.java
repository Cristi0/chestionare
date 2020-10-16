package com.management.chestionare.Controller;

import com.management.chestionare.Repository.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hello {

    @Autowired
    RepoUser users;

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("users", users.findAll());
        model.addAttribute("message", "Tymeleaf");
        return "ad/first.html";
    }

}