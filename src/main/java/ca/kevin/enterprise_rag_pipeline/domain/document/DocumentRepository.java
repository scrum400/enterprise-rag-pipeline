package ca.kevin.enterprise_rag_pipeline.domain.document;

import java.util.List;

public interface DocumentRepository {
    void save(Document document);
    List<Document> findAll();
}