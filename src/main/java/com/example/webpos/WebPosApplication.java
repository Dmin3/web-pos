package com.example.webpos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class WebPosApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebPosApplication.class, args);
	}
}
