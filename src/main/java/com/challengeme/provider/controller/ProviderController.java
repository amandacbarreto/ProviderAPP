package com.challengeme.provider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class ProviderController {

    @GetMapping("")
    public ModelAndView showLoginForm() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView showRegistrationForm() {
        ModelAndView mv = new ModelAndView("registration");
        return mv;
    }
}
