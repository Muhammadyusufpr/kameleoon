package com.company.kameleoon.dto.response;

import com.company.kameleoon.dto.request.ProfileRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProfileResponseDTO extends ProfileRequestDTO {
    private String id;
    private LocalDateTime createdDate;
}
