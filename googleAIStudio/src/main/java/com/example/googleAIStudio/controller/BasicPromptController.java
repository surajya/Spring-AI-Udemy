package com.example.googleAIStudio.controller;

import java.util.List;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BasicPromptController {

	private final ChatModel chatModel;

	public BasicPromptController(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	@GetMapping("/basicprompt")
	public ChatResponse emailResponse() {

		UserMessage userMessage = new UserMessage("java 11 latest features");

		SystemMessage systemMessage =
				new SystemMessage("You are a helpful assistant that provides information about Java 11 features.");

		Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
		System.out.println("Prompt: " + prompt);

		return chatModel.call(prompt);
	}
}
