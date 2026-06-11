package ca.kevin.enterprise_rag_pipeline.application.document;

import ca.kevin.enterprise_rag_pipeline.domain.document.DocumentChunk;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DocumentChunker {

    private static final int CHUNK_SIZE = 10; //500;

    public List<DocumentChunk> chunk(String content) {

        List<DocumentChunk> chunks = new ArrayList<>();

        for (int i = 0; i < content.length(); i += CHUNK_SIZE) {

            int end = Math.min(i + CHUNK_SIZE, content.length());

            chunks.add(
                    new DocumentChunk(
                            UUID.randomUUID().toString(),
                            content.substring(i, end)
                    )
            );
        }

        return chunks;
    }
}