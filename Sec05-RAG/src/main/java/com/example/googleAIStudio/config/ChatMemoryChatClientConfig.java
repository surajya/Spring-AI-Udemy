package com.example.googleAIStudio.config;

import com.example.googleAIStudio.advisor.TokenUsageAuditAdvisor;
import com.example.googleAIStudio.rag.PIIMaskingDocumentPostProcessor;
import org.springframework.ai.chat.cache.semantic.SemanticCacheAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.preretrieval.query.transformation.TranslationQueryTransformer;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public
class ChatMemoryChatClientConfig {

    @Bean
    public
    ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        return MessageWindowChatMemory.builder().maxMessages(10).chatMemoryRepository(jdbcChatMemoryRepository).build();
    }

    @Bean("chatMemoryChatClient")
    public
    ChatClient chatClient(ChatClient.Builder builder, ChatMemory chatMemory,
                          RetrievalAugmentationAdvisor retrievalAugmentationAdvisor, SemanticCacheAdvisor semanticCacheAdvisor) {
        Advisor simpleLoggerAdvisor = SimpleLoggerAdvisor.builder().build();
        Advisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        Advisor tokenUsageAuditAdvisor = new TokenUsageAuditAdvisor();
        return builder.defaultAdvisors(List.of(semanticCacheAdvisor, simpleLoggerAdvisor, messageChatMemoryAdvisor,
                tokenUsageAuditAdvisor, retrievalAugmentationAdvisor)).build();
    }

    @Bean
    public
    RetrievalAugmentationAdvisor retrievalAugmentationAdvisor(VectorStore vectorStore, ChatClient.Builder builder) {
        return RetrievalAugmentationAdvisor.builder()
                .queryTransformers(TranslationQueryTransformer.builder().chatClientBuilder(builder).targetLanguage("english").build())
                .documentRetriever(VectorStoreDocumentRetriever.builder().vectorStore(vectorStore).topK(3).similarityThreshold(0.5).build())
                .documentPostProcessors(PIIMaskingDocumentPostProcessor.builder())
                .build();
    }
}
