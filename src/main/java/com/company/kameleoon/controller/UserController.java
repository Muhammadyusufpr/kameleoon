package com.company.kameleoon.controller;

import com.company.kameleoon.dto.request.UserRequestDTO;
import com.company.kameleoon.dto.response.UserResponseDTO;
import com.company.kameleoon.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO dto) {
        log.info("Create user = {}", dto);
        return ResponseEntity.ok(userService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable String id) {
        log.info("Get user by id = {}", id);
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable String id, @RequestBody UserRequestDTO dto) {
        log.info("Update user by id{}{}", id, dto);
        return ResponseEntity.ok(userService.update(id, dto));
    }

}
