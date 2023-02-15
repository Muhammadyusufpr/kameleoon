package com.company.kameleoon.controller.auth;

import com.company.kameleoon.dto.detail.ApiResponse;
import com.company.kameleoon.dto.request.AuthRequestDTO;
import com.company.kameleoon.dto.request.LoginDTO;
import com.company.kameleoon.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody AuthRequestDTO dto) {
        log.info("Registration profile = {}", dto);
        return ResponseEntity.ok(authService.registration(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginDTO dto) {
        log.info("Login profile = {}", dto);
        return ResponseEntity.ok(authService.login(dto));
    }


}
