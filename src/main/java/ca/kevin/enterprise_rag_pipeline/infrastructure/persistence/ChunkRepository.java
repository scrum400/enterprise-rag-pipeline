package ca.kevin.enterprise_rag_pipeline.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChunkRepository
        extends JpaRepository<ChunkEntity, String> {
}