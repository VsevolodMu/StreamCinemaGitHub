package com.vistar.streamcinema.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "file_meta_data")
public class FileMetadata {

    @Id
    @Column(name = "file_id")
    private String id;

    @Column(name = "size_file")
    private long size;

    @Column(name = "content_type")
    private String httpContentType;
}