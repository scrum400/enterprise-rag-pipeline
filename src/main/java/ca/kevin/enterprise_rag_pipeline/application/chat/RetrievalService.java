package ca.kevin.enterprise_rag_pipeline.application.chat;

import ca.kevin.enterprise_rag_pipeline.domain.document.DocumentChunk;
import ca.kevin.enterprise_rag_pipeline.domain.retrieval.EmbeddingProvider;
import ca.kevin.enterprise_rag_pipeline.domain.retrieval.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetrievalService {

    private final EmbeddingProvider embeddingProvider;
    private final VectorStore vectorStore;

    public RetrievalService(
            EmbeddingProvider embeddingProvider,
            VectorStore vectorStore
    ) {
        this.embeddingProvider = embeddingProvider;
        this.vectorStore = vectorStore;
    }

    public List<DocumentChunk> retrieve(
            String question,
            int topK
    ) {

        List<Double> queryEmbedding =
                embeddingProvider.embed(question);

        return vectorStore.search(
                queryEmbedding,
                topK
        );
    }
}
