package com.example.googleAIStudio.controller;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
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
		this.chatClient = chatClientBuilder.defaultAdvisors(new SimpleLoggerAdvisor()).build();
	}

	@GetMapping("/chat-structure")
	public ResponseEntity<CountryCities> chatStructure(@RequestParam("message") String message) {
		CountryCities countryCities = chatClient.prompt().user(message).call().entity(CountryCities.class);
		return ResponseEntity.ok(countryCities);
	}

	@GetMapping("/chat-list")
	public ResponseEntity<List<String>> chatlist(@RequestParam("message") String message) {
		List<String> countryCities = chatClient.prompt().user(message).call().entity(new ListOutputConverter());
		return ResponseEntity.ok(countryCities);
	}

	@GetMapping("/chat-map")
	public ResponseEntity<Map<String, Object>> chatMap(@RequestParam("message") String message) {
		Map<String, Object> countryCities = chatClient.prompt().user(message).call().entity(new MapOutputConverter());
		return ResponseEntity.ok(countryCities);
	}

	@GetMapping("/chat-beanlist")
	public ResponseEntity<List<CountryCities>> chatbeanlist(@RequestParam("message") String message) {
		List<CountryCities> countryCities = chatClient.prompt().user(message).call()
				.entity(new ParameterizedTypeReference<List<CountryCities>>() {
				});
		return ResponseEntity.ok(countryCities);
	}
}
