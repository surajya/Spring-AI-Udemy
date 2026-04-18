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
public class PromptTemplateController {

	private final ChatClient chatClient;

	@Value("classpath:/promptTemplates/userEmailResponse.st")
	private Resource promptTemplate;

	public PromptTemplateController(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	@GetMapping("/email")
	public String emailResponse(@RequestParam("customerName") String customerName,
			@RequestParam("customerRequest") String customerRequest) {

		return chatClient.prompt()
				.system("""
						You are a professional customer support assistant.

						Strict Rules:
						- Follow the given template EXACTLY
						- Do not change format
						- Do not add extra content
						- Only generate the response part inside the template
						""")
				.user(promptTemplateSpec -> promptTemplateSpec.text(promptTemplate)
						.param("customerName", customerName)
						.param("customerRequest", customerRequest))
				.call().content();
	}
}
