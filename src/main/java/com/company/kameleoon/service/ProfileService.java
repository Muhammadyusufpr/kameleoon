package com.company.kameleoon.service;

import com.company.kameleoon.dto.detail.ApiResponse;
import com.company.kameleoon.dto.request.ProfileRequestDTO;
import com.company.kameleoon.dto.response.ProfileResponseDTO;
import com.company.kameleoon.entity.ProfileEntity;
import com.company.kameleoon.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileEntity get(String id) {
        return profileRepository.findById(id).orElse(null);
    }

    public ApiResponse<ProfileResponseDTO> getById(String id) {
        return new ApiResponse<>("Success!", false, toDTO(get(id)));
    }


    public ProfileResponseDTO toDTO(ProfileEntity entity) {
        ProfileResponseDTO dto = new ProfileResponseDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public ApiResponse<ProfileResponseDTO> update(String id, ProfileRequestDTO dto) {
        ProfileEntity entity = get(id);

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        profileRepository.save(entity);
        return new ApiResponse<>("Success!", false);
    }


}
