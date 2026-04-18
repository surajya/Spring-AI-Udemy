package com.example.ollama.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

	@Bean
	public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
		ChatClient.Builder chatClient = ChatClient.builder(ollamaChatModel);
		return chatClient.build();
	}

}
