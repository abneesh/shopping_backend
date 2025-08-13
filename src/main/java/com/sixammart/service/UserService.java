package com.sixammart.service;

import com.sixammart.dto.AuthResponse;
import com.sixammart.dto.LoginRequest;
import com.sixammart.dto.RegisterRequest;
import com.sixammart.entity.User;
import com.sixammart.repository.UserRepository;
import com.sixammart.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public AuthResponse register(RegisterRequest request) {
        // Check if user already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already registered");
        }
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone number is already registered");
        }

        // Create new user
        User user = new User();
        user.setFirstName(request.getFName());
        user.setLastName(request.getLName());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setLoginMedium(request.getLoginMedium());
        user.setSocialId(request.getSocialId());
        user.setRefCode(generateRefCode());
        
        // Handle referral
        if (request.getRefCode() != null && !request.getRefCode().isEmpty()) {
            Optional<User> referrer = userRepository.findByRefCode(request.getRefCode());
            if (referrer.isPresent()) {
                user.setReferredBy(referrer.get().getId());
            }
        }

        user = userRepository.save(user);

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(user.getEmail());

        return new AuthResponse(token, user);
    }

    public AuthResponse login(LoginRequest request) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getPhoneOrEmail(),
                request.getPassword()
            )
        );

        // Find user
        User user = userRepository.findByEmailOrPhone(request.getPhoneOrEmail(), request.getPhoneOrEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(user.getEmail());

        return new AuthResponse(token, user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    public User getUserByPhone(String phone) {
        return userRepository.findByPhone(phone)
            .orElseThrow(() -> new RuntimeException("User not found with phone: " + phone));
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPhone(userDetails.getPhone());
        user.setEmail(userDetails.getEmail());
        
        return userRepository.save(user);
    }

    public User updateFirebaseToken(Long userId, String firebaseToken) {
        User user = getUserById(userId);
        user.setFirebaseToken(firebaseToken);
        return userRepository.save(user);
    }

    public User verifyEmail(Long userId) {
        User user = getUserById(userId);
        user.setIsEmailVerified(true);
        user.setEmailVerifiedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User verifyPhone(Long userId) {
        User user = getUserById(userId);
        user.setIsPhoneVerified(true);
        user.setPhoneVerifiedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User updateWalletBalance(Long userId, Double amount) {
        User user = getUserById(userId);
        user.setWalletBalance(user.getWalletBalance() + amount);
        return userRepository.save(user);
    }

    public User updateLoyaltyPoints(Long userId, Integer points) {
        User user = getUserById(userId);
        user.setLoyaltyPoint(user.getLoyaltyPoint() + points);
        return userRepository.save(user);
    }

    public List<User> getAllActiveUsers() {
        return userRepository.findAllActiveUsers();
    }

    public Long getActiveUsersCount() {
        return userRepository.countActiveUsers();
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        user.setStatus(false);
        userRepository.save(user);
    }

    private String generateRefCode() {
        return "REF" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
