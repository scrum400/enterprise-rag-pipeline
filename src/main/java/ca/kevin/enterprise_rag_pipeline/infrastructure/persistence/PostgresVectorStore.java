package ca.kevin.enterprise_rag_pipeline.infrastructure.persistence;

import ca.kevin.enterprise_rag_pipeline.domain.document.DocumentChunk;
import ca.kevin.enterprise_rag_pipeline.domain.retrieval.VectorStore;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;

import java.util.Collections;
import java.util.List;

@Primary
@Component
public class PostgresVectorStore implements VectorStore {

    private final ChunkRepository repository;

    public PostgresVectorStore(
            ChunkRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public void add(
            DocumentChunk chunk,
            List<Double> embedding
    ) {

        ChunkEntity entity =
                new ChunkEntity(
                        chunk.id(),
                        chunk.content(),
                        toFloatArray(embedding)
                );

        repository.save(entity);
    }

    @Override
    public List<DocumentChunk> search(
            List<Double> queryEmbedding,
            int topK
    ) {
        System.out.println(
                "Using PostgreSQL vector search"
        );
        String vectorLiteral =
                toVectorLiteral(queryEmbedding);

        return repository.findNearest(
                        vectorLiteral,
                        topK
                )
                .stream()
                .map(entity ->
                        new DocumentChunk(
                                entity.getId(),
                                entity.getContent()
                        ))
                .toList();
    }

    private float[] toFloatArray(List<Double> embedding) {

        float[] result = new float[embedding.size()];

        for (int i = 0; i < embedding.size(); i++) {
            result[i] = embedding.get(i).floatValue();
        }

        return result;
    }
    private String toVectorLiteral(
            List<Double> embedding
    ) {

        return embedding.stream()
                .map(String::valueOf)
                .collect(
                        java.util.stream.Collectors.joining(
                                ",",
                                "[",
                                "]"
                        )
                );
    }

}