package com.example.googleAIStudio.config;

import com.example.googleAIStudio.advisor.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.cache.semantic.SemanticCacheAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenChatClient {

    @Bean("OpenChatClient")
    public ChatClient openChatClient(ChatClient.Builder chatClientBuilder, SemanticCacheAdvisor semanticCacheAdvisor) {
        return chatClientBuilder
                .defaultAdvisors(List.of(semanticCacheAdvisor, new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()))
                .build();
    }

}
