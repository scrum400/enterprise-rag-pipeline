package ca.kevin.enterprise_rag_pipeline.infrastructure.ollama;
import ca.kevin.enterprise_rag_pipeline.domain.llm.LlmProvider;
import org.springframework.stereotype.Component;

@Component
public class OllamaLlmProvider implements LlmProvider {

    @Override
    public String generate(String prompt) {
        return "Not implemented yet";
    }
}