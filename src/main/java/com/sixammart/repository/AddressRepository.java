package com.sixammart.repository;

import com.sixammart.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
    @Query("SELECT a FROM Address a WHERE a.user.id = :userId ORDER BY a.createdAt DESC")
    List<Address> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT a FROM Address a WHERE a.user.id = :userId AND a.addressType = :addressType")
    List<Address> findByUserIdAndAddressType(@Param("userId") Long userId, @Param("addressType") String addressType);
    
    @Query("SELECT a FROM Address a WHERE a.zoneId = :zoneId")
    List<Address> findByZoneId(@Param("zoneId") Long zoneId);
    
    @Query("SELECT COUNT(a) FROM Address a WHERE a.user.id = :userId")
    Long countByUserId(@Param("userId") Long userId);
}
