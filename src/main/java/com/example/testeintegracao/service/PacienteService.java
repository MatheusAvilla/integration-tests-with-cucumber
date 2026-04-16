package com.example.testeintegracao.service;

import com.example.testeintegracao.domain.Paciente;
import com.example.testeintegracao.exception.ServiceException;
import com.example.testeintegracao.metric.PacienteMetrics;
import com.example.testeintegracao.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMetrics pacienteMetrics;
    private final ObjectMapper objectMapper;

    public void processAndSave(String message) {
        try {
            log.info("Message: {}", message);
            Paciente paciente = objectMapper.readValue(message, Paciente.class);
            this.save(paciente);
        } catch (Exception e) {
            log.error("Error while processing message={}", message);
            throw new ServiceException("Error while processing message: " + e.getLocalizedMessage());
        }
    }

    public Paciente save(Paciente paciente) {
        //pacienteMetrics.increment();
        return pacienteRepository.save(paciente);
    }

}
