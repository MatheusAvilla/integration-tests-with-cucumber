package com.example.testeintegracao.message.consumer;

import com.example.testeintegracao.dto.PacienteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PacienteConsumer {

    @KafkaListener(
            topics = "pacientes.created",
            groupId = "paciente-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(PacienteEvent event) {
        log.info("Paciente consumed: {}", event);
    }

}
