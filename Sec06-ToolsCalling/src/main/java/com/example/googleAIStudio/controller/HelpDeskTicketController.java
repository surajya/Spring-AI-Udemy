package com.example.googleAIStudio.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api/ticket")
public class HelpDeskTicketController {

    private final ChatClient helpDestChatClient;

    public HelpDeskTicketController(@Qualifier("helpChatClient") ChatClient helpDestChatClient) {
        this.helpDestChatClient = helpDestChatClient;
    }

    @GetMapping("help-desk")
    public String helpDesk(@RequestHeader("username") String username, @RequestParam("query") String query) {
        return helpDestChatClient.prompt()
                .advisors(a -> a.param(CONVERSATION_ID, username))
                .toolContext(Map.of("username", username))
                .user(query)
                .call().content();
    }

}
