package ca.kevin.enterprise_rag_pipeline.infrastructure.persistence;

import ca.kevin.enterprise_rag_pipeline.domain.document.DocumentChunk;
import ca.kevin.enterprise_rag_pipeline.domain.retrieval.VectorStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryVectorStore implements VectorStore {

    private final List<Entry> entries = new ArrayList<>();

    @Override
    public void add(DocumentChunk chunk, List<Double> embedding) {
        entries.add(new Entry(chunk, embedding));
    }

    @Override
    public List<DocumentChunk> search(List<Double> queryEmbedding, int topK) {
        return entries.stream()
                .sorted((a, b) -> Double.compare(
                        cosineSimilarity(b.embedding(), queryEmbedding),
                        cosineSimilarity(a.embedding(), queryEmbedding)
                ))
                .limit(topK)
                .map(Entry::chunk)
                .toList();
    }

    private record Entry(
            DocumentChunk chunk,
            List<Double> embedding
    ) {
    }
    private double cosineSimilarity(
            List<Double> a,
            List<Double> b
    ) {

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < a.size(); i++) {
            dotProduct += a.get(i) * b.get(i);
            normA += a.get(i) * a.get(i);
            normB += b.get(i) * b.get(i);
        }

        return dotProduct /
                (Math.sqrt(normA) * Math.sqrt(normB));
    }

}