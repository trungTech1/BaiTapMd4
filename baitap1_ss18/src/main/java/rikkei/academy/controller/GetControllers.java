package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import rikkei.academy.model.EmailSettings;

@Controller
public class GetControllers {

    @GetMapping("/settings")
    public String book(Model model) {
        model.addAttribute("settings", new EmailSettings());
        return "settings";
    }

    @PostMapping("/settings")
    public String processForm(@ModelAttribute("settings") EmailSettings settings, Model model) {
        model.addAttribute("settings", settings);
        return "settingsSaved";
    }
}
