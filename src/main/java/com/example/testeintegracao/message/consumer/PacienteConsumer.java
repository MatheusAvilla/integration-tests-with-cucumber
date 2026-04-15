package com.example.testeintegracao.message.consumer;

import com.example.testeintegracao.dto.PacienteEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.util.Strings;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class PacienteConsumer {

    //@RetryableTopic(attempts = "4", exclude = {NullPointerException.class})
//    @RetryableTopic(attempts = "4") // 3 tópicos N-1
//    @KafkaListener(
//            topics = "pacientes.created",
//            groupId = "paciente-group",
//            containerFactory = "kafkaListenerContainerFactory"
//    )
//    public void consume(PacienteEvent event) {
//        log.info("Paciente consumed: {}", event);
//        if (Strings.isBlank(event.nome())) {
//            throw new RuntimeException("Campo obrigatório não preenchido");
//        }
//    }

    @KafkaListener(
            topics = "pacientes.created",
            containerFactory = "batchFactory"
            //concurrency = "3" Aumentar throughput com concorrência
    )
    public void batchConsumer(
            List<ConsumerRecord<String, String>> records,
            Acknowledgment ack) {

        log.info("Recebido batch com {} mensagens", records.size());

        records.forEach(batchRecord -> {
            log.info("Value: {}", batchRecord.value());
            log.info("Offset: {}", batchRecord.offset());
        });

        ack.acknowledge();
    }

    // Possíveis melhorias:

    // Persistir em um banco as mensagens que caírem na DLT, de modo que seja possível auditar e realizar correções manuais
    // Adicionar métricas para monitorar a quantidade e tipos de erros que ocorrem
//    @DltHandler
//    public void listenDLT(PacienteEvent pacienteEvent,
//                          @Header(KafkaHeaders.DLT_EXCEPTION_MESSAGE) String error) {
//        log.error("DLT Recebida: {} >>>>>> Erro: {}", pacienteEvent, error);
//    }

}
