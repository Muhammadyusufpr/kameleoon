package com.company.kameleoon.dto.response;

import com.company.kameleoon.dto.request.UserRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserResponseDTO extends UserRequestDTO {
    private String id;
    private LocalDateTime createdDate;
}
