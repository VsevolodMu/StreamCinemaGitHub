package com.vistar.streamcinema.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rooms")
public class Rooms extends BaseEntity<Long> {
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    public Instant createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    public Instant updateDate;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "time", nullable = false)
    private Instant time;

    @Size(max = 512)
    @NotNull
    @Column(name = "description", nullable = false, length = 512)
    private String description;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Films filmID;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Users creatorID;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roomID")
    private Set<RoomUsers> roomUsers;
}
