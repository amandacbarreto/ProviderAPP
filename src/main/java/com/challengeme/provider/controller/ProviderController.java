package com.challengeme.provider.controller;

import com.challengeme.provider.dto.ProviderDTO;
import com.challengeme.provider.dto.UserDTO;
import com.challengeme.provider.entity.*;
import com.challengeme.provider.repository.ProviderRepository;
import com.challengeme.provider.service.ProviderService;
import com.challengeme.provider.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping()
public class ProviderController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProviderService providerService;

    @GetMapping
    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
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
        return "redirect:/";
    }

    @GetMapping("/fornecedores")
    public ModelAndView showProviderList() {
        ModelAndView mv = new ModelAndView("list-provider");
        mv.addObject("providers", providerService.findAll());
        return mv;
    }

    @GetMapping("/fornecedores/cadastrar")
    public ModelAndView showProviderRegister() {
        ModelAndView mv = new ModelAndView("register-provider");
        Provider newProvider = new Provider();
        newProvider.getPhoneList().add(new Phone());
        newProvider.setAddress(new Address());
        mv.addObject("provider", newProvider);
        return mv;
    }

    @RequestMapping(value = "provider", params = {"save"})
    private String addProvider(@ModelAttribute Provider provider, HttpServletResponse response) throws IOException {
        providerService.insert(provider);
        return "redirect:/fornecedores";
    }

    @RequestMapping(value="provider", params={"addPhone"})
    public String addPhone(Provider provider, BindingResult bindingResult) {
        provider.getPhoneList().add(new Phone());
        return "register-provider";
    }

    @RequestMapping(value="provider", params={"removePhone"})
    public String removePhone(
            final Provider provider, final BindingResult bindingResult,
            final HttpServletRequest req) {
        final Integer id = Integer.valueOf(req.getParameter("removePhone"));
        provider.getPhoneList().remove(id.intValue());
        return "register-provider";
    }

    @GetMapping("/fornecedores/editar/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("register-provider");
        Provider provider = providerService.findById(id);
        mv.addObject("provider", provider);
        return mv;
    }

    @GetMapping("/fornecedores/deletar/{id}")
    public String deleteProvider(@PathVariable("id") String id, Model model) {
        providerService.deleteById(id);
        return "redirect:/fornecedores";
    }
}
