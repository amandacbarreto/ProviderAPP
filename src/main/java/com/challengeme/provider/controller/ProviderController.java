package com.challengeme.provider.controller;

import com.challengeme.provider.dto.UserDTO;
import com.challengeme.provider.entity.Address;
import com.challengeme.provider.entity.Phone;
import com.challengeme.provider.entity.Provider;
import com.challengeme.provider.service.ProviderService;
import com.challengeme.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return "redirect:/fornecedores/cadastrar";
    }

    @RequestMapping(value="provider", params={"addPhone"})
    public String addPhone(Provider provider, BindingResult bindingResult) {
        provider.getPhoneList().add(new Phone());
        return "register-provider";
    }

    @RequestMapping(value="/changePhoneList", params={"removePhone"})
    public String removePhone(
            final Provider provider, final BindingResult bindingResult,
            final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removePhone"));
        provider.getPhoneList().remove(rowId.intValue());
        return "register-provider";
    }
}
