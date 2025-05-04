package com.gudangdamar.main.dto;

public class LoginResponse {
    private String message;

    // Constructor
    public LoginResponse(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }
}
