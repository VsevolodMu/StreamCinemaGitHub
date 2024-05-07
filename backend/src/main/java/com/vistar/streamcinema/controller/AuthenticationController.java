package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.UsersInDto;
import com.vistar.streamcinema.dto.out.TokenDto;
import com.vistar.streamcinema.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("${auth.uri}")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody UsersInDto usersInDto) {
        authenticationService.register(usersInDto);
        return ResponseEntity.ok(usersInDto.username());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authenticate(
            @RequestBody AuthenticateRequest request,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request, response));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        authenticationService.logout(request, response);
        return ResponseEntity.ok("user successfully logout");
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refreshAccessToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(authenticationService.refresh(request, response));
    }

}
