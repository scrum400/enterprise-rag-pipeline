package ca.kevin.enterprise_rag_pipeline.api;

import ca.kevin.enterprise_rag_pipeline.application.chat.ChatRequest;
import ca.kevin.enterprise_rag_pipeline.application.chat.ChatResponse;
import ca.kevin.enterprise_rag_pipeline.application.chat.ChatService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String response = chatService.chat(request.message());
        return new ChatResponse(response);
    }
}