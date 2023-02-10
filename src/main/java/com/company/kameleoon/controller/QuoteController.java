package com.company.kameleoon.controller;

import com.company.kameleoon.dto.request.QuoteRequestDTO;
import com.company.kameleoon.dto.response.QuoteResponseDTO;
import com.company.kameleoon.service.QuoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quote")
public class QuoteController {
    private final QuoteService quoteService;

    @PostMapping("")
    public ResponseEntity<QuoteResponseDTO> create(@RequestBody QuoteRequestDTO dto) {
        log.info("Create quote={}", dto);
        return ResponseEntity.ok(quoteService.create(dto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<QuoteResponseDTO> getById(@PathVariable String id) {
        log.info("Get quote by id={}", id);
        return ResponseEntity.ok(quoteService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<QuoteResponseDTO> update(@PathVariable String id, @RequestBody QuoteRequestDTO dto) {
        log.info("Update quote id = {} data = {}", id, dto);
        return ResponseEntity.ok(quoteService.update(id, dto));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        log.info("Delete quote by id = {}", id);
        return ResponseEntity.ok(quoteService.delete(id));
    }



}
