package rikkei.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import rikkei.academy.model.Sandwich;

@Controller
public class GetControllers {
    @GetMapping("/list-book")
    public String book(Model model) {
        model.addAttribute("books", new Sandwich());
        return "/condiments";
    }
    @PostMapping("/sandwich")
    public String processForm(@ModelAttribute("sandwich") Sandwich sandwich, Model model) {
        model.addAttribute("selectedCondiments", sandwich.getCondiments());
        return "/list-book";
    }
}
