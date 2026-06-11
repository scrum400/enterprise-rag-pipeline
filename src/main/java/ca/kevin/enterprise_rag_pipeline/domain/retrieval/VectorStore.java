package ca.kevin.enterprise_rag_pipeline.domain.retrieval;

import ca.kevin.enterprise_rag_pipeline.domain.document.DocumentChunk;

import java.util.List;

public interface VectorStore {

    void add(DocumentChunk chunk, List<Double> embedding);

    List<DocumentChunk> search(List<Double> queryEmbedding, int topK);
}