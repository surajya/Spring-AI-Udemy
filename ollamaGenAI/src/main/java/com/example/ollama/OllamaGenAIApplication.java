package com.example.ollama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OllamaGenAIApplication {

	public static void main(String[] args) {
		SpringApplication.run(OllamaGenAIApplication.class, args);
		System.out.println("Ollama GenAI Application is running...");
	}

}
