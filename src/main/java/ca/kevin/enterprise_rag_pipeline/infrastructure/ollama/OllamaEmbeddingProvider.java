package ca.kevin.enterprise_rag_pipeline.infrastructure.ollama;

import ca.kevin.enterprise_rag_pipeline.domain.retrieval.EmbeddingProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OllamaEmbeddingProvider implements EmbeddingProvider {

    @Override
    public List<Double> embed(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}