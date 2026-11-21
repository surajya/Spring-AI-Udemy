package com.spy.mcpclient.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MCPToolsConfig {

    @Bean
    CommandLineRunner printTools(ToolCallbackProvider tools) {
        return args -> Arrays.stream(tools.getToolCallbacks())
                .forEach(t -> System.out.println("TOOL: " + t.getToolDefinition().name()));
    }

}
