package com.company.kameleoon.service;

import com.company.kameleoon.dto.detail.ApiResponse;
import com.company.kameleoon.dto.request.VotesRequestDTO;
import com.company.kameleoon.dto.response.VotesResponseDTO;
import com.company.kameleoon.entity.ProfileEntity;
import com.company.kameleoon.entity.QuoteEntity;
import com.company.kameleoon.entity.VotesEntity;
import com.company.kameleoon.repository.VotesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VotesService {
    private final VotesRepository votesRepository;
    private final QuoteService quoteService;
    private final ProfileService profileService;


    public ApiResponse<?> create(VotesRequestDTO dto) {

        ProfileEntity profile = profileService.get(dto.getProfileId());

        if (profile == null) {
            log.info("Profile not found!{}", dto.getProfileId());
            return new ApiResponse<>("Profile not found!", true);
        }


        QuoteEntity quote = quoteService.get(dto.getQuoteId());

        if (quote == null) {
            log.info("Quote not found! = {}", dto.getQuoteId());
            return new ApiResponse<>("Quote not found!", true);
        }

        VotesEntity entity = new VotesEntity();

        entity.setQuoteId(dto.getQuoteId());
        entity.setStatus(dto.getStatus());
        entity.setProfileId(dto.getProfileId());
        votesRepository.save(entity);
        return new ApiResponse<>("Success!", false);
    }

    public VotesEntity get(String id) {
        return votesRepository.findById(id).orElse(null);
    }

    public ApiResponse<VotesResponseDTO> getById(String id) {
        return new ApiResponse<>("Success!", false, toDTO(get(id)));
    }


    public VotesResponseDTO toDTO(VotesEntity entity) {
        VotesResponseDTO dto = new VotesResponseDTO();

        dto.setId(entity.getId());
        dto.setQuoteId(entity.getQuoteId());
        dto.setStatus(entity.getStatus());
        dto.setProfileId(entity.getProfileId());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }


    public ApiResponse<?> update(String id, VotesRequestDTO dto) {
        ProfileEntity profile = profileService.get(dto.getProfileId());

        if (profile == null) {
            log.info("Profile not found!{}", dto.getProfileId());
            return new ApiResponse<>("Profile not found!", true);
        }

        QuoteEntity quote = quoteService.get(dto.getQuoteId());

        if (quote == null) {
            log.info("Quote not found! = {}", dto.getQuoteId());
            return new ApiResponse<>("Quote not found!", true);
        }

        Optional<VotesEntity> optional = votesRepository.findById(id);

        if (optional.isEmpty()) {
            log.info("Votes not found!{}", id);
            return new ApiResponse<>("Votes not found!", true);
        }
        VotesEntity entity = optional.get();

        entity.setQuoteId(dto.getQuoteId());
        entity.setStatus(dto.getStatus());
        entity.setProfileId(dto.getProfileId());
        votesRepository.save(entity);
        return new ApiResponse<>("Success!", false);
    }


}
