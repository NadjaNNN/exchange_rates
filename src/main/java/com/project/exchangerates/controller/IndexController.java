package com.project.exchangerates.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, CsrfToken csrfToken) {
        String tokenValue = csrfToken.getToken();
        model.addAttribute("xsrfToken", tokenValue);
        System.out.println("Cookies token=" + tokenValue);
        return "index";
    }
}
