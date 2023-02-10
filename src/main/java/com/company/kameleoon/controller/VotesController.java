package com.company.kameleoon.controller;

import com.company.kameleoon.service.VotesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/votes")
public class VotesController {
    private final VotesService votesService;


}
