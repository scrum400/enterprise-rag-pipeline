package ca.kevin.enterprise_rag_pipeline.domain.retrieval;

import java.util.List;

public interface EmbeddingProvider {

    List<Double> embed(String text);
}