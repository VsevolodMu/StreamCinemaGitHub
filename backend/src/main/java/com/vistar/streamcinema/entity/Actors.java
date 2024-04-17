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
@Table(name = "actors")
public class Actors extends BaseEntity<Long> {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    public Instant createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    public Instant updateDate;

    @NotNull
    @Size(max = 32)
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @NotNull
    @Size(max = 32)
    @Column(name = "surname", nullable = false, length = 32)
    private String surname;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth", nullable = false)
    private Instant birth;


    @Size(max = 255)
    @Column(name = "biography", nullable = false)
    private String biography;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "actorID")
    private Set<FilmActors> filmActorsList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "actorID")
    private Set<UserActors> userActorsList;

}
