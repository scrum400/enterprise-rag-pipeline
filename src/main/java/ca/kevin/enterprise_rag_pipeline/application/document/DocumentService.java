package ca.kevin.enterprise_rag_pipeline.application.document;

import ca.kevin.enterprise_rag_pipeline.domain.document.Document;
import ca.kevin.enterprise_rag_pipeline.domain.document.DocumentRepository;
import ca.kevin.enterprise_rag_pipeline.domain.retrieval.EmbeddingProvider;
import ca.kevin.enterprise_rag_pipeline.domain.retrieval.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository repository;
    private final DocumentChunker chunker;
    private final EmbeddingProvider embeddingProvider;
    private final VectorStore vectorStore;

    public DocumentService(DocumentRepository repository,
                           DocumentChunker chunker,
                           EmbeddingProvider embeddingProvider,
                           VectorStore vectoreStore) {
        this.repository = repository;
        this.chunker = chunker;
        this.embeddingProvider = embeddingProvider;
        this.vectorStore = vectoreStore;
    }
    public void save(Document document) {

        repository.save(document);

        var chunks = chunker.chunk(document.content());

        for (var chunk : chunks) {

            var embedding =
                    embeddingProvider.embed(chunk.content());

            vectorStore.add(
                    chunk,
                    embedding
            );
        }
    }

    public List<Document> findAll() {
        return repository.findAll();
    }
}