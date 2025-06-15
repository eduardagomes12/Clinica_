package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication



@ComponentScan(basePackages = {
		"com.example.backend",        // controllers da API
		"com.example.core.models"     // para segurança
})
@EnableJpaRepositories(basePackages = {
		"com.example.core.reps"       // REPOSITÓRIOS AQUI!
})
@EntityScan(basePackages = {
		"com.example.core.models"     // entidades do core
})
public class BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
