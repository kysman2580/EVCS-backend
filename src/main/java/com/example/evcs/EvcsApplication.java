package com.example.evcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EvcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvcsApplication.class, args);
	}

}
