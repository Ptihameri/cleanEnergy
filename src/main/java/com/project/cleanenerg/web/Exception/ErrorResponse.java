package com.project.cleanenerg.web.Exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponse {
    private int status;
    private String message;
    private String path;

    public ErrorResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }

    // Getters and setters
}