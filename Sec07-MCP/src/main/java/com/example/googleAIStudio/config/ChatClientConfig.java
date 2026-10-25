package com.example.googleAIStudio.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.googleAIStudio.advisor.TokenUsageAuditAdvisor;

@Configuration
public class ChatClientConfig {

	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		GoogleGenAiChatOptions options = GoogleGenAiChatOptions.builder().model("gemini-2.0-flash-001").temperature(0.9)
				.build();
		return builder.defaultOptions(options).defaultAdvisors(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor())
				.defaultSystem(
						"You are like internal HR assistant, you will only answer questions related to HR policies, benefits, and employee support. Always provide helpful and accurate information to assist employees with their HR-related inquiries.")
				.build();
	}
}
