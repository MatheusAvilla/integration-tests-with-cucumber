package com.example.testeintegracao.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class TestMetrics {

    private final Counter testCounter;

    public TestMetrics(MeterRegistry registry) {
        this.testCounter = Counter.builder("test_counter_total")
                .description("Test counter")
                .register(registry);
    }

    @PostConstruct
    public void init() {
        testCounter.increment();
    }
}
