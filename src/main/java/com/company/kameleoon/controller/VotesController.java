package com.company.kameleoon.controller;

import com.company.kameleoon.dto.detail.ApiResponse;
import com.company.kameleoon.dto.request.VotesRequestDTO;
import com.company.kameleoon.dto.response.VotesResponseDTO;
import com.company.kameleoon.service.VotesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/votes")
public class VotesController {
    private final VotesService votesService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<?>> create(@RequestBody VotesRequestDTO dto) {
        log.info("Votes create = {}", dto);
        return ResponseEntity.ok(votesService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VotesResponseDTO>> getById(@PathVariable String id) {
        log.info("Votes get by id = {}", id);
        return ResponseEntity.ok(votesService.getById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable String id, @RequestBody VotesRequestDTO dto) {
        log.info("Votes update by id = {}", id);
        return ResponseEntity.ok(votesService.update(id, dto));
    }

}
