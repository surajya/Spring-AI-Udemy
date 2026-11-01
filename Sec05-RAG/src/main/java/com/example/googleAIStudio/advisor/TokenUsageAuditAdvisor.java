package com.example.googleAIStudio.advisor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;

public class TokenUsageAuditAdvisor implements CallAdvisor {

	private static final Logger logger = LoggerFactory.getLogger(TokenUsageAuditAdvisor.class);
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "TokenUsageAuditAdvisor";
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
		// TODO Auto-generated method stub
		ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
		ChatResponse chatResponse = chatClientResponse.chatResponse();
		if(chatResponse.getMetadata() != null) {
			Usage usage = chatResponse.getMetadata().getUsage();
			if(usage != null) {
				logger.info("toten usage details {}",usage.toString());
			}
			
		}
		return chatClientResponse;
	}

}
