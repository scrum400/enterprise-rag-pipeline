package ca.kevin.enterprise_rag_pipeline.infrastructure.ollama;

import ca.kevin.enterprise_rag_pipeline.domain.retrieval.EmbeddingProvider;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class OllamaEmbeddingProvider implements EmbeddingProvider {

    private final EmbeddingModel embeddingModel;

    public OllamaEmbeddingProvider(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }
    @Override
    public List<Double> embed(String text) {

        float[] vector = embeddingModel.embed(text);

        List<Double> result = new ArrayList<>();

        for (float value : vector) {
            result.add((double) value);
        }

        return result;
    }

}