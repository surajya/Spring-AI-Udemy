package com.example.googleAIStudio.config;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatModelConfig {

	@Bean
	public ChatModel chatModel(ChatModel chatModel) {
		return chatModel;
	}
}
