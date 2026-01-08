package com.example.testeintegracao.dto;

public record PacienteEvent(
        Long id,
        String nome,
        String cpf,
        String email
) {}
