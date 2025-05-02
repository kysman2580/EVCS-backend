package com.example.evcs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.evcs.notice.model.dao")
@SpringBootApplication
public class EvcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvcsApplication.class, args);
	}

}
