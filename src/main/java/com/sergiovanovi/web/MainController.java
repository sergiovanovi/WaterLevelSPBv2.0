package com.sergiovanovi.web;

import com.sergiovanovi.AuthorizedUser;
import com.sergiovanovi.model.User;
import com.sergiovanovi.model.enums.Role;
import com.sergiovanovi.service.MeterService;
import com.sergiovanovi.service.UserService;
import com.sergiovanovi.util.MeterParserAndMailSender;
import com.sergiovanovi.util.PasswordUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.Collections;

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

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("error") String hasError, Model model) {
        if (hasError.equals("true")) model.addAttribute("error", "Invalid/not confirm login and password");
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/confirm")
    public String confirmPage(@ModelAttribute("username") String username, @ModelAttribute("password") String password, Model model) {
        User user = userService.getByEmail(username);
        if (user != null && PasswordUtil.isMatch(password, user.getPassword())) {
            user.setEnabled(true);
            if (userService.save(user) != null) {
                LOG.info(LocalDateTime.now() + " " + username + " is enabled");
                model.addAttribute("error", "Account is activate, pls sing in");
                return "login";
            }
        }
        LOG.error(LocalDateTime.now() + " " + username + " was not find/save");
        model.addAttribute("error", "The email you are trying to confirm is not registered");
        return "register";
    }

    @PostMapping("/register")
    public String registerPage(@ModelAttribute("username") String username, @ModelAttribute("password") String password, Model model) {
        User tryUser = userService.getByEmail(username);
        if (tryUser == null) {
            User newUser = new User(username, PasswordUtil.encode(password), -10, 10, Collections.singleton(Role.ROLE_USER));
            if (userService.save(newUser) != null) {
                MeterParserAndMailSender.sendEmail(username,
                        "Confirm your account on WLSPB",
                        "Click the link to confirm the registration http://localhost:8080/wlspb/confirm?username=" + username + "&password=" + password);
                model.addAttribute("error", "Confirm registration by link in your email");
                return "login";
            }
        }
        LOG.error(LocalDateTime.now() + " " + username +" is already registered");
        model.addAttribute("error", "The email you have entered is already registered");
        return "register";
    }

    @GetMapping("/profile")
    public String metersList(Model model) {
        model.addAttribute("meters", meterService.getAll());
        model.addAttribute("lastMeter", meterService.getLast());
        model.addAttribute("user", userService.getByEmail(AuthorizedUser.email()));
        LOG.info(LocalDateTime.now() + " Send to profile");
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

    @GetMapping("/users")
    public String usersList(Model model) {
        model.addAttribute("users", userService.getAll());
        LOG.info(LocalDateTime.now() + " Send to users");
        return "users";
    }

    @PostMapping("/users")
    public String editUser(@ModelAttribute("email") String email, @ModelAttribute("enabled") String enabled,
                           @ModelAttribute("min") double min, @ModelAttribute("max") double max,
                           @ModelAttribute("util") int util, Model model) {
        boolean enabl = false;
        if (enabled.equals("true")) enabl = true;
        User user = userService.getByEmail(email);
        if (user != null) {
            user.setMin(min);
            user.setMax(max);
            user.setUtil(util);
            user.setEnabled(enabl);
            if (userService.save(user) != null) {
                LOG.info(LocalDateTime.now() + " Edit user: " + email);
            } else {
                LOG.error(LocalDateTime.now() + " Can not find user with email: " + email);
            }
        }
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        if (userService.delete(id)) {
            LOG.info(LocalDateTime.now() + " Delete user with id:" + id);
        } else {
            LOG.error(LocalDateTime.now() + " Can not delete user with id:" + id);
        }
        return "redirect:/users";
    }
}
