package com.company.kameleoon.dto.response;

import com.company.kameleoon.dto.request.QuoteRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class QuoteResponseDTO extends QuoteRequestDTO {
    private String id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
