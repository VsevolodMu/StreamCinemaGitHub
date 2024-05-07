package com.vistar.streamcinema.service;

import com.vistar.streamcinema.util.Range;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface VideoService {

    UUID save(MultipartFile video);

    DefaultVideoService.ChunkWithMetadata fetchChunk(UUID uuid, Range range);
}