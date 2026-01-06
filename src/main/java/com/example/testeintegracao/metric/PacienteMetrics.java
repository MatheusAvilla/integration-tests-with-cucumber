package com.example.testeintegracao.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class PacienteMetrics {

    private final Counter pacienteCreatedCounter;

    public PacienteMetrics(MeterRegistry registry) {
            this.pacienteCreatedCounter = Counter.builder("pacientes_saved_total")
                .description("Total number of pacientes created")
                .register(registry);
    }

    public void increment() {
        System.out.println(">>> Paciente metric incremented");
        pacienteCreatedCounter.increment();
    }

}
