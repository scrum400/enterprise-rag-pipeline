package ca.kevin.enterprise_rag_pipeline.application.chat;

import ca.kevin.enterprise_rag_pipeline.domain.llm.LlmProvider;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final LlmProvider llmProvider;

    public ChatService(LlmProvider llmProvider) {
        this.llmProvider = llmProvider;
    }

    public String chat(String message) {
        return llmProvider.generate(message);
    }
}