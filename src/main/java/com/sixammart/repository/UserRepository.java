package com.sixammart.repository;

import com.sixammart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByPhone(String phone);
    
    Optional<User> findByEmailOrPhone(String email, String phone);
    
    Boolean existsByEmail(String email);
    
    Boolean existsByPhone(String phone);
    
    @Query("SELECT u FROM User u WHERE u.refCode = :refCode")
    Optional<User> findByRefCode(@Param("refCode") String refCode);
    
    @Query("SELECT u FROM User u WHERE u.status = true")
    java.util.List<User> findAllActiveUsers();
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.status = true")
    Long countActiveUsers();
}
