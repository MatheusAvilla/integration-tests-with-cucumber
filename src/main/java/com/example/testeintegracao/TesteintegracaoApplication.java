package com.example.testeintegracao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TesteintegracaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteintegracaoApplication.class, args);
	}

}
