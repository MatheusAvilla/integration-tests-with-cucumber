package com.example.testeintegracao.metric;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class RegistryDebug {

    public RegistryDebug(MeterRegistry registry) {
        System.out.println(">>> Registry in use: " + registry.getClass());
    }

}
