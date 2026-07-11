package com.example.googleAIStudio.config;

import java.util.List;

import com.example.googleAIStudio.advisor.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatMemoryChatClientConfig {

	@Bean
	public ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository){
		return MessageWindowChatMemory.builder().maxMessages(10).chatMemoryRepository(jdbcChatMemoryRepository).build();
	}

	@Bean("chatMemoryChatClient")
	public ChatClient chatClient(ChatClient.Builder builder, ChatMemory chatMemory) {
		Advisor simpleLoggerAdvisor = SimpleLoggerAdvisor.builder().build();
		Advisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
		Advisor tokenUsageAuditAdvisor =new TokenUsageAuditAdvisor();
		return builder.defaultAdvisors(List.of(simpleLoggerAdvisor, messageChatMemoryAdvisor, tokenUsageAuditAdvisor)).build();
	}
}
