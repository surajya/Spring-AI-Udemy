package com.example.googleAIStudio.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptStuffingController {

	private final ChatClient chatClient;

	@Value("classpath:/promptTemplates/leaveRStuffing.st")
	private Resource promptStuffing;

	public PromptStuffingController(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	@GetMapping("/leaveRequest")
	public String leaveQuery(@RequestParam("message") String message) {

		return chatClient.prompt()
				.system(promptStuffing)
				.user(message)
				.call().content();
	}
}
