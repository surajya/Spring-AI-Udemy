package com.example.googleAIStudio.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.googleAIStudio.model.CountryCities;

@RestController
@RequestMapping("/api")
public class StructureOutputController {

	private final ChatClient chatClient;

	public StructureOutputController(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}

	@GetMapping("/chat-structure")
	public ResponseEntity<CountryCities> chat(@RequestParam("message") String message) {
		CountryCities countryCities = chatClient.prompt().user(message).call().entity(CountryCities.class);
		return ResponseEntity.ok(countryCities);
	}
}
