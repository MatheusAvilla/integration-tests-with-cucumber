package com.example.testeintegracao.service;

import com.example.testeintegracao.domain.Paciente;
import com.example.testeintegracao.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

}
