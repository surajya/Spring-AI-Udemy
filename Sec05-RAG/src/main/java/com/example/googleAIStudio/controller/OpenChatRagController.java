package com.example.googleAIStudio.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OpenChatRagController {

    private final ChatClient chatClient;

    public OpenChatRagController(@Qualifier("OpenChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/open-chat")
    public String openChat(@RequestParam(value = "message") String message) {
        return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
    }
}
