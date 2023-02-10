package com.company.kameleoon.service;

import com.company.kameleoon.dto.request.UserRequestDTO;
import com.company.kameleoon.dto.response.UserResponseDTO;
import com.company.kameleoon.entity.UserEntity;
import com.company.kameleoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public UserResponseDTO create(UserRequestDTO dto) {
        Optional<UserEntity> optional = userRepository.findByEmail(dto.getEmail());

        if (optional.isPresent()) {
            log.info("Email already exists!{}", dto.getEmail());
            return null;
        }

        UserEntity entity = new UserEntity();

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        userRepository.save(entity);
        return toDTO(entity);
    }

    public UserEntity get(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserResponseDTO getById(String id) {
        return toDTO(get(id));
    }


    public UserResponseDTO toDTO(UserEntity entity) {
        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
//        dto.setPassword(entity.getPassword());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public UserResponseDTO update(String id, UserRequestDTO dto) {
        UserEntity entity = get(id);

        if (entity == null) {
            log.info("User not found!{}", id);
            return null;
        }
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        userRepository.save(entity);
        return toDTO(entity);
    }


}
