package com.vistar.streamcinema.controller;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    String password;
    String email;
    int favoriteGenreID;
    String profileInfo;
    String number;
    @Temporal(TemporalType.TIMESTAMP)
    Instant registrationDate;
    @Temporal(TemporalType.TIMESTAMP)
    Instant lastLoginDate;
    @Temporal(TemporalType.TIMESTAMP)
    Instant birth;
}
