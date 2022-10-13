package com.challengeme.provider.controller;

import com.challengeme.provider.dto.UserDTO;
import com.challengeme.provider.service.ProviderService;
import com.challengeme.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class ProviderController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProviderService providerService;

    @GetMapping("")
    public ModelAndView showLoginForm() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView showRegistrationForm(UserDTO userDTO) {
        ModelAndView mv = new ModelAndView("registration");
        return mv;
    }

    @PostMapping("/adduser")
    public String addUser(UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        userService.insert(userDTO);
        return "redirect:/fornecedores";
    }

    @GetMapping("/fornecedores")
    public ModelAndView showProviderList() {
        ModelAndView mv = new ModelAndView("list-provider");
        return mv;
    }

    @GetMapping("/fornecedores/cadastrar")
    public ModelAndView showProviderRegister() {
        ModelAndView mv = new ModelAndView("add-provider");
        return mv;
    }
}
