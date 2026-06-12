package ca.kevin.enterprise_rag_pipeline.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "document_chunks")
public class ChunkEntity {

    @Id
    private String id;

    private String content;

    @Column(columnDefinition = "TEXT")
    private String embedding;

    public ChunkEntity() {
    }

    public ChunkEntity(
            String id,
            String content,
            String embedding
    ) {
        this.id = id;
        this.content = content;
        this.embedding = embedding;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getEmbedding() {
        return embedding;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEmbedding(String embedding) {
        this.embedding = embedding;
    }
}