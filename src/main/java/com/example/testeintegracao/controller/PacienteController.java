package com.example.testeintegracao.controller;

import com.example.testeintegracao.domain.Paciente;
import com.example.testeintegracao.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> cadastrar(@RequestBody Paciente paciente) {
        Paciente novoPaciente = pacienteService.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }

}
