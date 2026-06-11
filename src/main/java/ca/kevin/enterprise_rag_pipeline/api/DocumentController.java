package ca.kevin.enterprise_rag_pipeline.api;

import ca.kevin.enterprise_rag_pipeline.application.document.DocumentService;
import ca.kevin.enterprise_rag_pipeline.application.document.DocumentChunker;
import ca.kevin.enterprise_rag_pipeline.domain.document.Document;
import ca.kevin.enterprise_rag_pipeline.domain.document.DocumentChunk;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
//@RequestMapping("/api/documents")
//@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentChunker documentChunker;

    public DocumentController(DocumentService documentService, DocumentChunker documentChunker) {
        this.documentService = documentService;
        this.documentChunker = documentChunker;
    }

    @PostMapping
    public void upload(@RequestBody String content) {
        documentService.save(
                new Document(
                        UUID.randomUUID().toString(),
                        content
                )
        );
    }

    @GetMapping
    public List<Document> getAll() {
        return documentService.findAll();
    }

    @GetMapping("/api/chunks")
    public List<DocumentChunk> chunks(@RequestParam String text) {
        return documentChunker.chunk(text);
    }
}