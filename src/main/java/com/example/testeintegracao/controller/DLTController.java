package com.example.testeintegracao.controller;

import com.example.testeintegracao.dto.PacienteEvent;
import com.example.testeintegracao.service.DLTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Opção de reprocessamento 3:
//Criação de endpoint para receber um evento esperado
//E realizar a postagem no tópico
//Para tentar processar com sucesso

@RestController
@RequiredArgsConstructor
@RequestMapping("/dlt")
public class DLTController {

    private final DLTService dltService;

    @PostMapping("/reprocess")
    public void reprocess(@RequestBody PacienteEvent event) {
        dltService.reprocessPacienteCreation(event);
    }

}
