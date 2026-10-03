package com.example.googleAIStudio.config;

import com.example.googleAIStudio.advisor.TokenUsageAuditAdvisor;
import com.example.googleAIStudio.tools.HelpDeskTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
public class HelpDeskChatClientConfig {

    @Value("classpath:/promptTemplates/helpdeskticket.st")
    private Resource promptTemplate;

    @Bean("helpChatClient")
    public ChatClient getChatClient(ChatClient.Builder builder, ChatMemory chatMemory, HelpDeskTools helpDeskTools) {
        Advisor simpleLoggerAdvisor = SimpleLoggerAdvisor.builder().build();
        Advisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        Advisor tokenUsageAuditAdvisor = new TokenUsageAuditAdvisor();
        return builder
                .defaultSystem(promptTemplate)
                .defaultTools(helpDeskTools)
                .defaultAdvisors(List.of(simpleLoggerAdvisor, messageChatMemoryAdvisor, tokenUsageAuditAdvisor))
                .build();
    }
}
