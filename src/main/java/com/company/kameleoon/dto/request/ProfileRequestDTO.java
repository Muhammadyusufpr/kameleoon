package com.company.kameleoon.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ProfileRequestDTO {
    private String name;
    private String email;
    private String password;

}
