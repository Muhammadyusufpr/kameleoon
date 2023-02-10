package com.company.kameleoon.service;

import com.company.kameleoon.repository.VotesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VotesService {
    private final VotesRepository votesRepository;


}
