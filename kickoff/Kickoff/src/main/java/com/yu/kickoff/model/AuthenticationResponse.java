package com.yu.kickoff.model;

public class AuthenticationResponse {
    private User user;
    private String message;

    public AuthenticationResponse(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}
