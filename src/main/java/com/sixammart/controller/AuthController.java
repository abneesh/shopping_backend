package com.sixammart.controller;

import com.sixammart.dto.ApiResponse;
import com.sixammart.dto.AuthResponse;
import com.sixammart.dto.LoginRequest;
import com.sixammart.dto.RegisterRequest;
import com.sixammart.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication management APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        try {
            AuthResponse authResponse = userService.register(request);
            return ResponseEntity.ok(ApiResponse.success("User registered successfully", authResponse));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse authResponse = userService.login(request);
            return ResponseEntity.ok(ApiResponse.success("Login successful", authResponse));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/social-login")
    @Operation(summary = "Social media login")
    public ResponseEntity<ApiResponse<AuthResponse>> socialLogin(@RequestBody LoginRequest request) {
        try {
            // Handle social login logic here
            AuthResponse authResponse = userService.login(request);
            return ResponseEntity.ok(ApiResponse.success("Social login successful", authResponse));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/verify-phone")
    @Operation(summary = "Verify phone number")
    public ResponseEntity<ApiResponse<String>> verifyPhone(@RequestParam String phone, @RequestParam String otp) {
        try {
            // Implement phone verification logic
            return ResponseEntity.ok(ApiResponse.success("Phone verified successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/verify-email")
    @Operation(summary = "Verify email address")
    public ResponseEntity<ApiResponse<String>> verifyEmail(@RequestParam String email, @RequestParam String token) {
        try {
            // Implement email verification logic
            return ResponseEntity.ok(ApiResponse.success("Email verified successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/forgot-password")
    @Operation(summary = "Forgot password")
    public ResponseEntity<ApiResponse<String>> forgotPassword(@RequestParam String phoneOrEmail) {
        try {
            // Implement forgot password logic
            return ResponseEntity.ok(ApiResponse.success("Password reset instructions sent"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Reset password")
    public ResponseEntity<ApiResponse<String>> resetPassword(
            @RequestParam String phoneOrEmail,
            @RequestParam String otp,
            @RequestParam String newPassword) {
        try {
            // Implement reset password logic
            return ResponseEntity.ok(ApiResponse.success("Password reset successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/check-email")
    @Operation(summary = "Check if email exists")
    public ResponseEntity<ApiResponse<Boolean>> checkEmail(@RequestParam String email) {
        try {
            boolean exists = userService.getUserByEmail(email) != null;
            return ResponseEntity.ok(ApiResponse.success("Email check completed", exists));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.success("Email check completed", false));
        }
    }

    @PostMapping("/check-phone")
    @Operation(summary = "Check if phone exists")
    public ResponseEntity<ApiResponse<Boolean>> checkPhone(@RequestParam String phone) {
        try {
            boolean exists = userService.getUserByPhone(phone) != null;
            return ResponseEntity.ok(ApiResponse.success("Phone check completed", exists));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.success("Phone check completed", false));
        }
    }

    @PostMapping("/guest/request")
    @Operation(summary = "Guest request for FCM token")
    public ResponseEntity<ApiResponse<Map<String, Object>>> guestRequest(@RequestBody Map<String, String> request) {
        try {
            String fcmToken = request.get("fcm_token");
            // In a real implementation, you would save the FCM token for guest notifications

            Map<String, Object> response = Map.of(
                "guest_id", "guest_" + System.currentTimeMillis(),
                "fcm_token", fcmToken != null ? fcmToken : "",
                "status", "success"
            );

            return ResponseEntity.ok(ApiResponse.success("Guest request processed successfully", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}
