package com.company.kameleoon.service;

import com.company.kameleoon.dto.detail.ApiResponse;
import com.company.kameleoon.dto.request.QuoteRequestDTO;
import com.company.kameleoon.dto.response.QuoteResponseDTO;
import com.company.kameleoon.entity.QuoteEntity;
import com.company.kameleoon.entity.ProfileEntity;
import com.company.kameleoon.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final ProfileService userService;


    public ApiResponse<?> create(QuoteRequestDTO dto) {
        ProfileEntity user = userService.get(dto.getUserId());

        if (user == null) {
            log.info("User not found!{}", dto.getUserId());
            return new ApiResponse<>("User not found!", true);
        }
        QuoteEntity entity = new QuoteEntity();
        entity.setContent(dto.getContent());
        entity.setProfileId(dto.getUserId());
        quoteRepository.save(entity);
        return new ApiResponse<>("Success!", false);
    }

    public QuoteEntity get(String id) {
        return quoteRepository.findById(id).orElse(null);
    }

    public ApiResponse<QuoteResponseDTO> getById(String id) {
        return new ApiResponse<>("Success!", false, toDTO(get(id)));
    }


    public QuoteResponseDTO toDTO(QuoteEntity entity) {
        QuoteResponseDTO dto = new QuoteResponseDTO();

        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setUserId(entity.getProfileId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        return dto;
    }

    public ApiResponse<QuoteResponseDTO> update(String id, QuoteRequestDTO dto) {
        QuoteEntity entity = get(id);
        if (entity == null) {
            log.info("Quote not found!{}", id);
            return new ApiResponse<>("User not found!", true);
        }

        ProfileEntity user = userService.get(dto.getUserId());

        if (user == null) {
            log.info("User not found!{}", dto.getUserId());
            return null;
        }
        entity.setContent(dto.getContent());
        entity.setProfileId(dto.getUserId());
        entity.setUpdatedDate(LocalDateTime.now());
        quoteRepository.save(entity);
        return new ApiResponse<>("Success!", false, toDTO(entity));
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

    public ApiResponse<List<QuoteResponseDTO>> getTopQuotes() {
        List<QuoteResponseDTO> topQuotes = quoteRepository
                .getTopQuotes(PageRequest.of(0,10))
                .stream()
                .map(this::toDTO)
                .toList();
        return new ApiResponse<>("Success!", false, topQuotes);
    }

    public ApiResponse<List<QuoteResponseDTO>> getBadQuotes() {
        List<QuoteResponseDTO> badQuotes = quoteRepository
                .getBadQuotes(PageRequest.of(0,10))
                .stream()
                .map(this::toDTO)
                .toList();
//        List<QuoteResponseDTO> badQuotes = new LinkedList<>();
        return new ApiResponse<>("Success!", false, badQuotes);
    }


}
