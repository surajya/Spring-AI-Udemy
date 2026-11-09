package com.spy.mcpclient.controller;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mcp")
public class MCPClientController {

    private final ChatClient  chatClient;

    public MCPClientController(ChatClient.Builder builder, ToolCallbackProvider  toolCallbackProvider) {
        this.chatClient = builder
                .defaultTools(toolCallbackProvider)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @GetMapping("/chat")
    public String chat(@RequestHeader(value = "username", required = false) String username, @RequestParam String message) {
        return chatClient.prompt().user(message+"username is :"+username).call().content();
    }
}
