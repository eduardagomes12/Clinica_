package com.example.frontoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.core.reps")
@EntityScan(basePackages = "com.example.core.models")
@ComponentScan(basePackages = {
		"com.example.frontoffice.controllers",
		"com.example.backend.DTO",
		"com.example.core.services"
})
public class FrontofficeApplication {
	public static void main(String[] args) {
		SpringApplication.run(FrontofficeApplication.class, args);
	}
}
