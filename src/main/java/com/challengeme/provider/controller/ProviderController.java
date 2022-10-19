package com.challengeme.provider.controller;

import com.challengeme.provider.dto.ProviderDTO;
import com.challengeme.provider.dto.UserDTO;
import com.challengeme.provider.entity.*;
import com.challengeme.provider.service.ProviderService;
import com.challengeme.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping()
public class ProviderController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProviderService providerService;

    private static final String REGISTER_PROVIDER = "register-provider";
    private static final ModelAndView REGISTER_PROVIDER_MV = new ModelAndView(REGISTER_PROVIDER);

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
        return new ModelAndView("registration");
    }

    @PostMapping("/adduser")
    public String addUser(UserDTO userDTO, BindingResult result, Model model) {
        if(Boolean.TRUE.equals(userService.isNewUser(userDTO))){
            userService.insert(userDTO);
            return "redirect:/";
        } else {
            return "redirect:/registration-error";
        }
    }

    @RequestMapping("/registration-error")
    public String registrationError(Model model, UserDTO userDTO) {
        model.addAttribute("registrationError", true);
        return "registration";
    }

    @GetMapping("/fornecedores")
    public ModelAndView showProviderList() {
        ModelAndView mv = new ModelAndView("list-provider");
        mv.addObject("providers", providerService.findAll());
        return mv;
    }

    @GetMapping("/fornecedores/cadastrar")
    public ModelAndView showProviderRegister() {
        Provider newProvider = new Provider();
        newProvider.getPhoneList().add(new Phone());
        newProvider.setAddress(new Address());
        REGISTER_PROVIDER_MV.addObject("provider", newProvider);
        return REGISTER_PROVIDER_MV;
    }

    @RequestMapping(value = "provider", params = {"save"})
    public String addProvider(@ModelAttribute ProviderDTO provider, HttpServletResponse response) {
        providerService.insert(provider);
        return "redirect:/fornecedores";
    }

    @RequestMapping(value="/provider", params={"addPhone"})
    public String addRow(final ProviderDTO provider, final BindingResult bindingResult) {
        provider.getPhoneList().add(new Phone());
        return REGISTER_PROVIDER;
    }

    @RequestMapping(value="/provider", params={"removePhone"})
    public String removeRow(
            final ProviderDTO provider, final BindingResult bindingResult,
            final HttpServletRequest req) {
        final Integer id = Integer.parseInt(req.getParameter("removePhone"));
        provider.getPhoneList().remove(id.intValue());
        return REGISTER_PROVIDER;
    }

    @GetMapping("/fornecedores/editar/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") String id) {
        Provider provider = providerService.findById(id);
        REGISTER_PROVIDER_MV.addObject("provider", provider);
        return REGISTER_PROVIDER_MV;
    }

    @GetMapping("/fornecedores/deletar/{id}")
    public String deleteProvider(@PathVariable("id") String id, Model model) {
        providerService.deleteById(id);
        return "redirect:/fornecedores";
    }
}
