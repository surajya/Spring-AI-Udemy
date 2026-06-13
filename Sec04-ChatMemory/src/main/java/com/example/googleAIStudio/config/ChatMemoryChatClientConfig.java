package com.example.googleAIStudio.config;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatMemoryChatClientConfig {

	@Bean("chatMemoryChatClient")
	public ChatClient chatClient(ChatClient.Builder builder, ChatMemory chatMemory) {
		Advisor simpleLoggerAdvisor = SimpleLoggerAdvisor.builder().build();
		Advisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
		return builder.defaultAdvisors(List.of(simpleLoggerAdvisor, messageChatMemoryAdvisor)).build();
	}
}
