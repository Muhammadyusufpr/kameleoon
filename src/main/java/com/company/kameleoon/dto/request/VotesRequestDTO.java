package com.company.kameleoon.dto.request;

import com.company.kameleoon.enums.VotesStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VotesRequestDTO {
    private String profileId;
    private String quoteId;
    private VotesStatus status;
}
