package com.example.testeintegracao.message.producer;

import com.example.testeintegracao.dto.PacienteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PacienteProducer {

    private final KafkaTemplate<String, PacienteEvent> kafkaTemplate;

    public void send(PacienteEvent event) {
        log.info("Try to produce event={}", event);
        kafkaTemplate.send("pacientes.created", event.id().toString(), event);
    }

}
