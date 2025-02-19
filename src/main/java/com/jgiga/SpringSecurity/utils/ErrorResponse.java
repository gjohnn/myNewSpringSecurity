package com.jgiga.SpringSecurity.utils;

import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private List<String> details;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.details = null;
    }
}
