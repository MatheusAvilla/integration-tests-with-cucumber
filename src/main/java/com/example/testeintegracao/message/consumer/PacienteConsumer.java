package com.example.testeintegracao.message.consumer;

import com.example.testeintegracao.dto.PacienteEvent;
import com.example.testeintegracao.exception.ServiceException;
import com.example.testeintegracao.service.PacienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
@Slf4j
@RequiredArgsConstructor
public class PacienteConsumer {

    private final PacienteService pacienteService;

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

        List<Future<?>> futures = new ArrayList<>();

        records.forEach(batchRecord -> futures.add(virtualThreadExecutor().submit(() ->
                pacienteService.processAndSave(batchRecord.value()))));

        futures.forEach(message -> {
            try {
                message.get();
            } catch (Exception e) {
                throw new ServiceException("Error consuming messages: " + e.getLocalizedMessage());
            }
        });

        ack.acknowledge();
    }

    @Bean
    public ExecutorService virtualThreadExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
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
