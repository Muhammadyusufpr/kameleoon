package com.company.kameleoon.service;

import com.company.kameleoon.dto.request.QuoteRequestDTO;
import com.company.kameleoon.dto.response.QuoteResponseDTO;
import com.company.kameleoon.entity.QuoteEntity;
import com.company.kameleoon.entity.UserEntity;
import com.company.kameleoon.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserService userService;


    public QuoteResponseDTO create(QuoteRequestDTO dto) {
        UserEntity user = userService.get(dto.getUserId());

        if (user == null) {
            log.info("User not found!{}", dto.getUserId());
            return null;
        }
        QuoteEntity entity = new QuoteEntity();

        entity.setContent(dto.getContent());
        entity.setUserId(dto.getUserId());
        quoteRepository.save(entity);
        return toDTO(entity);
    }

    public QuoteEntity get(String id) {
        return quoteRepository.findById(id).orElse(null);
    }

    public QuoteResponseDTO getById(String id) {
        return toDTO(get(id));
    }


    public QuoteResponseDTO toDTO(QuoteEntity entity) {
        QuoteResponseDTO dto = new QuoteResponseDTO();

        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setUserId(entity.getUserId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        return dto;
    }

    public QuoteResponseDTO update(String id, QuoteRequestDTO dto) {
        QuoteEntity entity = get(id);
        if (entity == null) {
            log.info("Quote not found!{}", id);
            return null;
        }

        UserEntity user = userService.get(dto.getUserId());

        if (user == null) {
            log.info("User not found!{}", dto.getUserId());
            return null;
        }
        entity.setContent(dto.getContent());
        entity.setUserId(dto.getUserId());
        quoteRepository.save(entity);
        return toDTO(entity);
    }


    public Boolean delete(String id) {
        QuoteEntity entity = get(id);

        if (entity == null) {
            log.info("Quote not found!{}", id);
            return false;
        }
        quoteRepository.delete(entity);
        return true;
    }


}
