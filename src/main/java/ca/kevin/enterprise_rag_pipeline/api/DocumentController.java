package ca.kevin.enterprise_rag_pipeline.api;

import ca.kevin.enterprise_rag_pipeline.application.chat.RetrievalService;
import ca.kevin.enterprise_rag_pipeline.application.document.DocumentService;
import ca.kevin.enterprise_rag_pipeline.application.document.DocumentChunker;
import ca.kevin.enterprise_rag_pipeline.domain.retrieval.EmbeddingProvider;
import ca.kevin.enterprise_rag_pipeline.domain.document.Document;
import ca.kevin.enterprise_rag_pipeline.domain.document.DocumentChunk;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
//@RequestMapping("/api/documents")
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentChunker documentChunker;
    private final EmbeddingProvider embeddingProvider;
    private final RetrievalService retrievalService;

    public DocumentController(DocumentService documentService,
                              DocumentChunker documentChunker,
                              EmbeddingProvider embeddingProvider,
                              RetrievalService retrievalService) {
        this.documentService = documentService;
        this.documentChunker = documentChunker;
        this.embeddingProvider = embeddingProvider;
        this.retrievalService  = retrievalService;
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

    @GetMapping("/embedding")
    public List<Double> embedding(@RequestParam String text) {
        return embeddingProvider.embed(text);
    }

    @GetMapping("/embedding-size")
    public int embeddingSize(
            @RequestParam String text
    ) {
        return embeddingProvider.embed(text).size();
    }

    @GetMapping("/search")
    public List<DocumentChunk> search(
            @RequestParam String query
    ) {
        return retrievalService.retrieve(query, 3);
    }

}