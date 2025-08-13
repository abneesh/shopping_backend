package com.sixammart.repository;

import com.sixammart.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    
    @Query("SELECT z FROM Zone z WHERE z.status = true ORDER BY z.name ASC")
    List<Zone> findAllActiveZones();
    
    @Query("SELECT z FROM Zone z WHERE z.name LIKE %:name% AND z.status = true")
    List<Zone> findByNameContainingIgnoreCase(@Param("name") String name);
    
    @Query("SELECT z FROM Zone z WHERE z.cashOnDelivery = true AND z.status = true")
    List<Zone> findCashOnDeliveryZones();
    
    @Query("SELECT z FROM Zone z WHERE z.digitalPayment = true AND z.status = true")
    List<Zone> findDigitalPaymentZones();
    
    @Query("SELECT COUNT(z) FROM Zone z WHERE z.status = true")
    Long countActiveZones();
}
