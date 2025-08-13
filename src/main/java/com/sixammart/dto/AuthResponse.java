package com.sixammart.dto;

import com.sixammart.entity.User;

public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";
    private User user;

    // Constructors
    public AuthResponse() {}

    public AuthResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getTokenType() { return tokenType; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
