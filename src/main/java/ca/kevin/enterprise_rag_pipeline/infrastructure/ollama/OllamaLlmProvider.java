package ca.kevin.enterprise_rag_pipeline.infrastructure.ollama;

import ca.kevin.enterprise_rag_pipeline.domain.llm.LlmProvider;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Component;

@Component
public class OllamaLlmProvider implements LlmProvider {

    private final ChatModel chatModel;

    public OllamaLlmProvider(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String generate(String prompt) {
        return chatModel.call(prompt);
    }
}