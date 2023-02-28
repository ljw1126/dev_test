package dev.treenod.controller;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class IndexController {

    private final Environment env;

    public IndexController(Environment env) {
        this.env = env;
    }

    @RequestMapping("/")
    public String index(Model model, HttpSession session) {
        model.addAttribute("profile", getProfile());
        model.addAttribute("sessionId", session.getId());
        return "index";
    }

    private String getProfile() {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst().orElse("");
    }
}