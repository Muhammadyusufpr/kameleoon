package com.company.kameleoon.dto.response;

import com.company.kameleoon.dto.request.VotesRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class VotesResponseDTO extends VotesRequestDTO {
    private String id;
    private LocalDateTime createdDate;
}
