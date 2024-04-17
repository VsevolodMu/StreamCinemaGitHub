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
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chats")
@Component
public class Chats extends BaseEntity<Long> {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 32)
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @NotNull
    @Size(max = 255)
    @Column(name = "user_status", nullable = false)
    private String userStatus;

    @Size(max = 1024)
    @Column(name = "last_message", nullable = false, length = 1024)
    private String lastMessage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chatID")
    private Set<ChatUsers> chatUsersList;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "last_message_time", nullable = false)
    public Instant lastMessageTime;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    public Instant createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    public Instant updateDate;
}
