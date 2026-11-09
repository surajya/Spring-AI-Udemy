package com.spy.mcpclient.controller;

import com.spy.mcpclient.util.ToolUtil;
import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mcp")
public class MCPClientController {

    private final ChatClient  chatClient;
    private final List<McpSyncClient> mcpSyncClients;

    public MCPClientController(ChatClient.Builder builder, ToolCallbackProvider  toolCallbackProvider, List<McpSyncClient> mcpSyncClientList) {
        this.chatClient = builder
                //.defaultTools(toolCallbackProvider)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
        this.mcpSyncClients = mcpSyncClientList;
    }

    @GetMapping("/chat")
    public String chat(@RequestHeader(value = "username", required = false) String username, @RequestParam String message) {
        ToolCallback[]  toolCallbacks = ToolUtil.selectToolsFor(mcpSyncClients, "helpdesk-mcp-server", null);
        return chatClient.prompt()
                .tools(toolCallbacks)
                .user(message+"username is :"+username).call().content();
    }
}
