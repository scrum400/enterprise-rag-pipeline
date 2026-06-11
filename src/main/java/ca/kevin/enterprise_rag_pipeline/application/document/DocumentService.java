package ca.kevin.enterprise_rag_pipeline.application.document;

import ca.kevin.enterprise_rag_pipeline.domain.document.Document;
import ca.kevin.enterprise_rag_pipeline.domain.document.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository repository;

    public DocumentService(DocumentRepository repository) {
        this.repository = repository;
    }

    public void save(Document document) {
        repository.save(document);
    }

    public List<Document> findAll() {
        return repository.findAll();
    }
}