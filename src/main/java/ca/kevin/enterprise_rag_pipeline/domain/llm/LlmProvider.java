package ca.kevin.enterprise_rag_pipeline.domain.llm;

public interface LlmProvider {
    String generate(String prompt);
}
