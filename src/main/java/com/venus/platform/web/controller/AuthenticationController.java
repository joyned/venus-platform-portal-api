package com.venus.platform.web.controller;

import com.venus.platform.core.service.AuthenticationService;
import com.venus.platform.web.dto.AuthenticationDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationDTO authenticationDTO) {
        service.generateRefreshToken(authenticationDTO.getEmail(), authenticationDTO.getPassword());
        String accessToken = service.generateAccessToken(authenticationDTO.getEmail());
        return ResponseEntity.ok(accessToken);
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestHeader("Authorization") String authorization) {
        String accessToken;
        try {
            accessToken = service.refreshToken(authorization.replace("Bearer ", ""));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(accessToken);
    }
}
