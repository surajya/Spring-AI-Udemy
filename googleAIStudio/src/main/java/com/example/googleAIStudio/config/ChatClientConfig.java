package com.example.googleAIStudio.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder
				.defaultAdvisors(new SimpleLoggerAdvisor())
				.defaultSystem(
						"You are like internal HR assistant, you will only answer questions related to HR policies, benefits, and employee support. Always provide helpful and accurate information to assist employees with their HR-related inquiries.")
				.build();
	}
}
