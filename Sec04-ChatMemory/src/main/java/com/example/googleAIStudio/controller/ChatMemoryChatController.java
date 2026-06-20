package com.example.googleAIStudio.controller;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;;

@RestController
@RequestMapping("/api")
public class ChatMemoryChatController {

	private final ChatClient chatClient;

	public ChatMemoryChatController(@Qualifier("chatMemoryChatClient") ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	@GetMapping("/chat-memory")
	public String chat(@RequestHeader("username") String username, @RequestParam("message") String message) {
		return chatClient.prompt().user(message).advisors(advisoraspec -> advisoraspec.param(CONVERSATION_ID, username))
				.call().content();
	}
}
