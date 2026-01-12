package com.example.testeintegracao.service;

import com.example.testeintegracao.dto.PacienteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DLTService {

    private final KafkaTemplate<String, PacienteEvent> kafkaTemplate;

    public void reprocessPacienteCreation(PacienteEvent event) {
        try {
            log.info("Tentativa de reprocessar criação de paciente: {}", event);
            kafkaTemplate.send("pacientes.created", event.id().toString(), event);
        } catch (Exception e) {
            log.error("Erro ao reprocessar paciente: {}", e.getLocalizedMessage());
            throw new RuntimeException("Erro ao reprocessar paciente: " + e.getMessage());
        }

    }

}
