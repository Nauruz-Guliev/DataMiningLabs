package ru.kpfu.itis.gnt.hwpebble.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.gnt.hwpebble.constants.ViewPaths;


@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return ViewPaths.HOME;
    }
}
