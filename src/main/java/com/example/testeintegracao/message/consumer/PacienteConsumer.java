package com.example.testeintegracao.message.consumer;

import com.example.testeintegracao.dto.PacienteEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PacienteConsumer {

    //@RetryableTopic(attempts = "4", exclude = {NullPointerException.class})
    @RetryableTopic(attempts = "4") // 3 tópicos N-1
    @KafkaListener(
            topics = "pacientes.created",
            groupId = "paciente-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(PacienteEvent event) {
        log.info("Paciente consumed: {}", event);
        if (Strings.isBlank(event.nome())) {
            throw new RuntimeException("Campo obrigatório não preenchido");
        }
    }

    @DltHandler
    public void listenDLT(PacienteEvent pacienteEvent) {
        log.info("DLT Recebida: {}", pacienteEvent);
    }

}
