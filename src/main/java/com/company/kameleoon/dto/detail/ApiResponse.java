package com.company.kameleoon.dto.detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private String message;
    private Boolean isError;
    private T date;

    public ApiResponse() {
    }

    public ApiResponse(String message, Boolean isError) {
        this.message = message;
        this.isError = isError;
        this.date = null;
    }

    public ApiResponse(Boolean isError, T date) {
        this.isError = isError;
        this.date = date;
    }

    public ApiResponse(Boolean isError) {
        this.isError = isError;
    }

    public ApiResponse(String message, Boolean isError, T date) {
        this.message = message;
        this.isError = isError;
        this.date = date;
    }
}
