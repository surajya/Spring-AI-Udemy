package com.example.googleAIStudio.config;

import com.example.googleAIStudio.advisor.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.cache.semantic.SemanticCacheAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenChatClient {

    @Bean("OpenChatClient")
    public ChatClient openChatClient(ChatClient.Builder builder, SemanticCacheAdvisor semanticCacheAdvisor) {
        GoogleGenAiChatOptions.Builder options = GoogleGenAiChatOptions.builder()
                //.model("gemini-2.0-flash-001")  //gemini-2.0-flash-001
                .temperature(0.9);

        return builder
                .defaultOptions(options)
                .defaultAdvisors(
                        semanticCacheAdvisor,
                        new SimpleLoggerAdvisor(),
                        new TokenUsageAuditAdvisor()
                )
                .defaultSystem(
                        "You are a helpful general-purpose AI assistant."
                )
                .build();
    }

}
