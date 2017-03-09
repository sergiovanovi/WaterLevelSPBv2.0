package com.sergiovanovi.web;

import com.sergiovanovi.AuthorizedUser;
import com.sergiovanovi.service.MeterService;
import com.sergiovanovi.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MainController {
    private static final Logger LOG = getLogger("application");

    @Autowired
    private MeterService meterService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home(Model model) {
        LOG.info("send to profile");
        return "redirect:/profile";
    }

    @RequestMapping(value = "/profile")
    public String metersList(Model model) {
        model.addAttribute("meters", meterService.getAll());
        model.addAttribute("lastMeter", meterService.getLast());
        model.addAttribute("user", userService.getByEmail(AuthorizedUser.get().getUsername()));
        LOG.info("send to meters");
        return "profile";
    }

    @RequestMapping(value = "/login")
    public String loginPage(Model model) {
        return "login";
    }

    @RequestMapping(value = {"/users"})
    public String usersList(Model model) {
        model.addAttribute("users", userService.getAll());
        LOG.info("send to users");
        return "profile";
    }
}
