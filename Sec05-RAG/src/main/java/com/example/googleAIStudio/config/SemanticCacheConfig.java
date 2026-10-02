package com.example.googleAIStudio.config;

import org.springframework.ai.chat.cache.semantic.SemanticCache;
import org.springframework.ai.chat.cache.semantic.SemanticCacheAdvisor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.ai.vectorstore.redis.cache.semantic.DefaultSemanticCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public
class SemanticCacheConfig {

//    @Bean
//    public
//    RedisClient getRedisClient(
//            @Value("${spring.data.redis.host:localhost}")
//            String host,
//            @Value("${spring.data.redis.port:6379}")
//            int port
//    ) {
//        return RedisClient.builder().hostAndPort(host, port).build();
//    }

    //    @Bean
//    public
//    SemanticCache getSemanticCache(RedisClient redisClient, EmbeddingModel embeddingModel) {
//        return DefaultSemanticCache.builder()
//                .jedisClient(redisClient)
//                .embeddingModel(embeddingModel)
//                .similarityThreshold(0.9)
//                .indexName("eazybytes-semantic-cache")
//                .prefix("cache:")
//                .build();
//    }

    //create bean of vectorStore
    @Bean("cacheVectorStrore")
    public VectorStore cacheVectorStore(JdbcTemplate jdbcTemplate, EmbeddingModel embeddingModel) {
        return PgVectorStore.builder(jdbcTemplate, embeddingModel)
                .schemaName("semantic_cache")
                .dimensions(1536)
                .initializeSchema(true)
                .build();
    }

    @Bean
    public SemanticCache getSemanticCache(@Qualifier("cacheVectorStrore") VectorStore vectorStore, EmbeddingModel embeddingModel) {
        return DefaultSemanticCache.builder()
                .vectorStore(vectorStore)
                .embeddingModel(embeddingModel)
                .similarityThreshold(0.5)
                .build();
    }

    //write code for create bean of semanticCacheAdvisor
    @Bean
    public SemanticCacheAdvisor getSemanticCacheAdvisor(SemanticCache semanticCache) {
        return SemanticCacheAdvisor.builder()
                .cache(semanticCache)
                .order(Ordered.HIGHEST_PRECEDENCE)
                .build();
    }
}
