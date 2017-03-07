package com.sergiovanovi.web;

import com.sergiovanovi.service.MeterService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MainController {
    private static final Logger LOG = getLogger(MainController.class);

    @Autowired
    private MeterService meterService;

    @RequestMapping("/")
    public String home(Model model){
        LOG.info("send to index");
        return "index";
    }

    @RequestMapping(value = "/profile")
    public String metersList(Model model){
        model.addAttribute("meters", meterService.getAll());
        model.addAttribute("lastMeter", meterService.getLast());
        LOG.info("send to meters");
        return "profile";
    }
}
