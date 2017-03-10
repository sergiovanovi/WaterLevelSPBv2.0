package com.sergiovanovi.web;

import com.sergiovanovi.AuthorizedUser;
import com.sergiovanovi.model.User;
import com.sergiovanovi.service.MeterService;
import com.sergiovanovi.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MainController {
    private static final Logger LOG = getLogger("application");

    @Autowired
    private MeterService meterService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        LOG.info(LocalDateTime.now() + " Send to profile");
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String metersList(Model model) {
        model.addAttribute("meters", meterService.getAll());
        model.addAttribute("lastMeter", meterService.getLast());
        model.addAttribute("user", userService.getByEmail(AuthorizedUser.email()));
        LOG.info(LocalDateTime.now() + " Send to meters");
        return "profile";
    }

    @PostMapping("/profile")
    public String save(@ModelAttribute("max") double max, @ModelAttribute("min") double min) {
        User user = userService.getByEmail(AuthorizedUser.email());
        user.setMin(min);
        user.setMax(max);
        userService.save(user);
        LOG.info(LocalDateTime.now() + " New limits saved");
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("error") String hasError, Model model) {
        if (hasError.equals("true")) model.addAttribute("error", "Invalid login and password");
        return "login";
    }

    @GetMapping("/users")
    public String usersList(Model model) {
        model.addAttribute("users", userService.getAll());
        LOG.info(LocalDateTime.now() + " Send to users");
        return "profile";
    }
}
