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
@Table(name = "films")
public class Films extends BaseEntity<Long> {

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

    @NotNull
    @Size(max = 128)
    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @NotNull
    @Size(max = 2048)
    @Column(name = "description", nullable = false, length = 2048)
    private String description;

    @NotNull
    @Column(name = "rate", nullable = false)
    private double rate;

    @Size(max = 1024)
    @Column(name = "cover", length = 1024)
    private String cover;

    @NotNull
    @Size(max = 51200)
    @Column(name = "path", nullable = false, length = 51200)
    private String path;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "filmID")
    private Set<FilmGenres> filmGenresList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "filmID")
    private Set<FilmActors> filmActorsList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "filmID")
    private Set<FilmStatus> filmStatusList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "filmID")
    private Set<Comments> commentsList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "filmID")
    private Set<Rooms> roomsList;
}
