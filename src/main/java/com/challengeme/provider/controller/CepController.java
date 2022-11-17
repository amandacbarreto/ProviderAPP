package com.challengeme.provider.controller;


import com.challengeme.provider.entity.Address;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/searchCEP")
public class CepController {

    @GetMapping(value = "/{cep}")
    public Address searchCEPtest(@PathVariable String cep) {
        String url = "https://viacep.com.br/ws/"+cep+"/json/";
        RestTemplate restTemplate = new RestTemplate();
        Address addressTest = restTemplate.getForObject(url, Address.class);
        return addressTest;
    }
}
