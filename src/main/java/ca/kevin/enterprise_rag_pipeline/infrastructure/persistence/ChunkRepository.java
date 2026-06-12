package ca.kevin.enterprise_rag_pipeline.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChunkRepository
        extends JpaRepository<ChunkEntity, String> {
    @Query(value = """
    SELECT *
    FROM document_chunks
    ORDER BY embedding <=> CAST(:embedding AS vector)
    LIMIT :topK
    """,
            nativeQuery = true)
    List<ChunkEntity> findNearest(
            @Param("embedding") String embedding,
            @Param("topK") int topK
    );
}