package ca.kevin.enterprise_rag_pipeline.infrastructure.persistence;

import ca.kevin.enterprise_rag_pipeline.domain.document.Document;
import ca.kevin.enterprise_rag_pipeline.domain.document.DocumentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryDocumentRepository implements DocumentRepository {

    private final List<Document> documents = new ArrayList<>();

    @Override
    public void save(Document document) {
        documents.add(document);
    }

    @Override
    public List<Document> findAll() {
        return List.copyOf(documents);
    }
}
