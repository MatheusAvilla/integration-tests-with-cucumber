package com.example.testeintegracao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Endpoint público - Não é necessária a autenticação";
    }

    @GetMapping("/private")
    public String securedEndpoint() {
        return "Endpoint privado - Autenticado com LDAP";
    }

}
