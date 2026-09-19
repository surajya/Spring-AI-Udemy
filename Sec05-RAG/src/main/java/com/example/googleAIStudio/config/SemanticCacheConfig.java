package com.example.googleAIStudio.config;

import org.springframework.ai.chat.cache.semantic.SemanticCache;
import org.springframework.ai.chat.cache.semantic.SemanticCacheAdvisor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.redis.cache.semantic.DefaultSemanticCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import redis.clients.jedis.RedisClient;

@Configuration
public
class SemanticCacheConfig {

    @Bean
    public
    RedisClient getRedisClient(
            @Value("${spring.data.redis.host:localhost}")
            String host,
            @Value("${spring.data.redis.port:6379}")
            int port
    ) {
        return RedisClient.builder().hostAndPort(host, port).build();
    }

    @Bean
    public
    SemanticCache getSemanticCache(RedisClient redisClient, EmbeddingModel embeddingModel) {
        return DefaultSemanticCache.builder()
                .jedisClient(redisClient)
                .embeddingModel(embeddingModel)
                .similarityThreshold(0.9)
                .indexName("eazybytes-semantic-cache")
                .prefix("cache:")
                .build();
    }

    //write code for create bean of semanticCacheAdvisor
    @Bean
    public
    SemanticCacheAdvisor getSemanticCacheAdvisor(SemanticCache semanticCache) {
        return SemanticCacheAdvisor.builder()
                .cache(semanticCache)
                .order(Ordered.HIGHEST_PRECEDENCE)
                .build();
    }
}
