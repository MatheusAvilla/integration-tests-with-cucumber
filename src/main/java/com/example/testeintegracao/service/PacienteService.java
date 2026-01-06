package com.example.testeintegracao.service;

import com.example.testeintegracao.domain.Paciente;
import com.example.testeintegracao.metric.PacienteMetrics;
import com.example.testeintegracao.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMetrics pacienteMetrics;

    public Paciente save(Paciente paciente) {
        pacienteMetrics.increment();
        return pacienteRepository.save(paciente);
    }

}
