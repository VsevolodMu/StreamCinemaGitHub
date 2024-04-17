package com.vistar.streamcinema.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "film_status")
public class FilmStatus extends BaseEntity<Long> {
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private Users userID;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Films filmID;

    @NotNull
    @Column(name = "planned", nullable = false)
    private boolean planned;

    @NotNull
    @Column(name = "watched", nullable = false)
    private boolean watched;

    @NotNull
    @Column(name = "favorite", nullable = false)
    private boolean favorite;
}
