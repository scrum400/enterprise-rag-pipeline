package ca.kevin.enterprise_rag_pipeline.application.chat;

import ca.kevin.enterprise_rag_pipeline.domain.document.DocumentChunk;
import ca.kevin.enterprise_rag_pipeline.domain.llm.LlmProvider;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ChatService {

    private final LlmProvider llmProvider;
    private final RetrievalService retrievalService;

    public ChatService(
            LlmProvider llmProvider,
            RetrievalService retrievalService
    ) {
        this.llmProvider = llmProvider;
        this.retrievalService = retrievalService;
    }

    public String chat(String message) {

        var chunks = retrievalService.retrieve(message, 3);

        String context = chunks.stream()
                .map(DocumentChunk::content)
                .collect(Collectors.joining("\n"));

        String prompt = """
                Context:
                %s

                Question:
                %s

                Answer:
                """.formatted(context, message);

        return llmProvider.generate(prompt);
    }
}