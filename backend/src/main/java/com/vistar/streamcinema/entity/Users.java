package com.vistar.streamcinema.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "users")
public class Users extends BaseEntity<Long> {

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
    @Column(name = "user_id", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 48)
    @Column(name = "username", nullable = false, length = 48, unique = true)
    private String username;

    @NotNull
    @Size(max = 64)
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @NotNull
    @Size(max = 128)
    @Email
    @Column(name = "email", nullable = false, length = 128)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "last_login_date", nullable = false)
    private Instant lastLoginDate;

    @NotNull
    @Column(name = "favorite_genre", nullable = false)
    private int favoriteGenreID;

    @NotNull
    @Size(max = 255)
    @Column(name = "profile_info", nullable = false, length = 255)
    private String profileInfo;

    @NotNull
    @Size(max = 12)
    @Column(name = "number", nullable = false, length = 12)
    private String number;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "birth", nullable = false)
    private Instant birth;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userID")
    private Set<UserGenres> userGenresList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userID")
    private Set<FilmStatus> filmStatusList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userID")
    private Set<UserActors> userActorsList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userID")
    private Set<RoomUsers> roomUsers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userID")
    private Set<Comments> commentsList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creatorID")
    private Set<Rooms> roomsList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "senderID")
    private Set<Messages> messagesList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "requestID")
    private Set<Friends> friendsRequesterList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipientID")
    private Set<Friends> friendsRecipientList;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userID")
    private Subscriptions subscriptions;
}
