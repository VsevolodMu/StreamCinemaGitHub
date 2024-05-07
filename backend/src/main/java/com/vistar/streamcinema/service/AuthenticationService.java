package com.vistar.streamcinema.service;

import com.vistar.streamcinema.controller.AuthenticateRequest;
import com.vistar.streamcinema.dto.in.UsersInDto;
import com.vistar.streamcinema.dto.out.TokenDto;
import com.vistar.streamcinema.entity.Role;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.mapper.UsersMapper;
import com.vistar.streamcinema.repositories.UsersRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional("transactionManager")
public class AuthenticationService {
    private final JwtService jwtService;
    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsersMapper userMapper;
    private static final String PREFIX = "Bearer ";


    public String register(UsersInDto usersInDto) {
        Users users = userMapper.toEntity(usersInDto);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRole(Role.USER);
        var savedUser = userRepository.save(users);
        String token = UUID.randomUUID().toString();
        return token;
    }

    public TokenDto authenticate(
            AuthenticateRequest request,
            HttpServletResponse response) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        Instant instant = Instant.now();
        user.setLastLoginDate(instant);
        var savedUser = userRepository.save(user);
        jwtService.revokeAllUserToken(user);
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        jwtService.saveUserToken(refreshToken, user);
        Cookie refreshCookie = new Cookie("refresh_token", refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true);
        response.addCookie(refreshCookie);
        return TokenDto.builder()
                .accessToken(accessToken)
                .username(user.getUsername())
                .build();

    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace(PREFIX, "");
        var user = userRepository.findByUsername(jwtService.extractUsername(token)).orElseThrow();
        jwtService.revokeAllUserToken(user);
        SecurityContextHolder.clearContext();
        Cookie refreshCookie = new Cookie("refresh_token", null);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true);
        response.addCookie(refreshCookie);
    }

    public TokenDto refresh(HttpServletRequest request, HttpServletResponse response) {
        var result = TokenDto.builder().build();
        Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("refresh_token")).findFirst()
                .ifPresentOrElse(
                        cookie -> {
                            if (jwtService.isTokenRevoked(cookie.getValue())) {
                                response.addCookie(new Cookie("refresh_token", null));
                                System.out.println("this token is revoked");
                            } else {
                                var user = userRepository.findByUsername(
                                        jwtService.extractUsername(
                                                cookie.getValue()
                                        )
                                ).orElseThrow();
                                jwtService.revokeAllUserToken(user);
                                result.setAccessToken(jwtService.generateAccessToken(user));
                                var refreshToken = jwtService.generateRefreshToken(user);
                                jwtService.saveUserToken(refreshToken, user);
                                Cookie refreshCookie = new Cookie("refresh_token", refreshToken);
                                refreshCookie.setHttpOnly(true);
                                refreshCookie.setSecure(true);
                                response.addCookie(refreshCookie);
                            }
                        },
                        () -> {
                            System.out.println("cookie has no refresh token");
                        }
                );
        return result;
    }
}
