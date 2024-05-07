package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileMetadataRepository extends JpaRepository<FileMetadata, String> {
}