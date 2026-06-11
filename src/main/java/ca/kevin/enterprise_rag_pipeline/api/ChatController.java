package ca.kevin.enterprise_rag_pipeline.api;

import ca.kevin.enterprise_rag_pipeline.application.chat.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public String chat() {
        return chatService.chat("Hello");
    }
}