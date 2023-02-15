package com.company.kameleoon.controller;

import com.company.kameleoon.dto.detail.ApiResponse;
import com.company.kameleoon.dto.request.ProfileRequestDTO;
import com.company.kameleoon.dto.response.ProfileResponseDTO;
import com.company.kameleoon.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfileResponseDTO>> getById(@PathVariable String id) {
        log.info("Get user by id = {}", id);
        return ResponseEntity.ok(profileService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfileResponseDTO>> update(@PathVariable String id, @RequestBody ProfileRequestDTO dto) {
        log.info("Update user by id{}{}", id, dto);
        return ResponseEntity.ok(profileService.update(id, dto));
    }

}
