package com.example.testeintegracao.message.consumer;

import com.example.testeintegracao.dto.PacienteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//Opção de reprocessamento 1:
//Criação de um consumidor para o tópico DLT
//De modo que a mensagem que cair no DLT
//Será repostada ao tópico original
//Na tentativa de normalizar o processamento

@Component
@RequiredArgsConstructor
@Slf4j
public class PacienteDLTReprocessorConsumer {

    private final KafkaTemplate<String, PacienteEvent> kafkaTemplate;

    // Opção de reprocessamento 2:
    // Utilizar a anotação scheduled do Spring para criar uma job que irá tentar repostar
    // as mensagens no tópico original a partir de um intervalo de tempo definido

    // @Scheduled(cron = "0 */10 * * * *")
    @KafkaListener(
            topics = "pacientes.created.DLT",
            groupId = "paciente-dlt-reprocessor",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void reprocess(PacienteEvent event) {
        log.info("Reprocessando DLT evento: {}", event);
        if (event.nome() == null || event.nome().isBlank()) {
            log.warn("Evento inválido, ignorando: {}", event);
            return;
        }
        kafkaTemplate.send("pacientes.created", event.id().toString(), event);
    }

}
