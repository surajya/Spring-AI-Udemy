package com.example.googleAIStudio.rag;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentLoader {

    private final VectorStore vectorStore;

    private final JdbcTemplate jdbcTemplate;

    @Value("classpath:Eazybytes_HR_Policies.pdf")
    Resource resource;

    public DocumentLoader(VectorStore vectorStore, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadDocumentIntoVectorStore() {
        TikaDocumentReader documentReader = new TikaDocumentReader(resource);
        List<Document> documents = documentReader.get();
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM vector_store", Integer.class);
        if (count != null && count > 0) {
            System.out.println("Vector store already populated, skipping ingestion");
            return;
        }else{
        // your existing document loading + vectorStore.add(documents) here
            System.out.println("Vector store empty, add document into vector_store");
            TextSplitter textSplitter = TokenTextSplitter.builder().withChunkSize(200).withMaxNumChunks(400).build();
            vectorStore.add(textSplitter.split(documents));
        }
    }
}