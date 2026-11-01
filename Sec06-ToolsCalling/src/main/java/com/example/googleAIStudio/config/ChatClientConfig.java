package com.example.googleAIStudio.config;

import com.example.googleAIStudio.advisor.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        GoogleGenAiChatOptions.Builder options = GoogleGenAiChatOptions.builder()
                //.model("gemini-2.0-flash-001")  //gemini-2.0-flash-001
                .temperature(0.8);

        return builder
                .defaultOptions(options)
                .defaultAdvisors(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor())
                .defaultSystem(
                        "You are like internal HR assistant, you will only answer questions related to HR policies, benefits, and employee support. Always provide helpful and accurate information to assist employees with their HR-related inquiries.")
                .build();
    }
}
