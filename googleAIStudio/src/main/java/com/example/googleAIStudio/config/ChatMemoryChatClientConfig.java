package com.example.googleAIStudio.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatMemoryChatClientConfig {

	@Bean("chatMemoryChatClient")
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}
}
