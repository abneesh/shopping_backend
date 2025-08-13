package com.sixammart.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String fName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lName;

    @NotBlank(message = "Phone is required")
    @Size(max = 15, message = "Phone must not exceed 15 characters")
    private String phone;

    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String refCode;
    private String loginMedium;
    private String socialId;

    // Constructors
    public RegisterRequest() {}

    public RegisterRequest(String fName, String lName, String phone, String email, String password) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public String getFName() { return fName; }
    public void setFName(String fName) { this.fName = fName; }

    public String getLName() { return lName; }
    public void setLName(String lName) { this.lName = lName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRefCode() { return refCode; }
    public void setRefCode(String refCode) { this.refCode = refCode; }

    public String getLoginMedium() { return loginMedium; }
    public void setLoginMedium(String loginMedium) { this.loginMedium = loginMedium; }

    public String getSocialId() { return socialId; }
    public void setSocialId(String socialId) { this.socialId = socialId; }
}
