package com.sixammart.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "Phone or email is required")
    private String phoneOrEmail;

    @NotBlank(message = "Password is required")
    private String password;

    private String loginMedium;
    private String socialId;

    // Constructors
    public LoginRequest() {}

    public LoginRequest(String phoneOrEmail, String password) {
        this.phoneOrEmail = phoneOrEmail;
        this.password = password;
    }

    // Getters and Setters
    public String getPhoneOrEmail() { return phoneOrEmail; }
    public void setPhoneOrEmail(String phoneOrEmail) { this.phoneOrEmail = phoneOrEmail; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getLoginMedium() { return loginMedium; }
    public void setLoginMedium(String loginMedium) { this.loginMedium = loginMedium; }

    public String getSocialId() { return socialId; }
    public void setSocialId(String socialId) { this.socialId = socialId; }
}
