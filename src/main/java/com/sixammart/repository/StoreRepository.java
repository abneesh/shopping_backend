package com.sixammart.repository;

import com.sixammart.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    
    @Query("SELECT s FROM Store s WHERE s.status = true AND s.active = true")
    List<Store> findAllActiveStores();
    
    @Query("SELECT s FROM Store s WHERE s.status = true AND s.active = true")
    Page<Store> findAllActiveStores(Pageable pageable);
    
    @Query("SELECT s FROM Store s WHERE s.zoneId = :zoneId AND s.status = true AND s.active = true")
    List<Store> findByZoneIdAndActive(@Param("zoneId") Long zoneId);
    
    @Query("SELECT s FROM Store s WHERE s.moduleId = :moduleId AND s.status = true AND s.active = true")
    List<Store> findByModuleIdAndActive(@Param("moduleId") Long moduleId);
    
    @Query("SELECT s FROM Store s WHERE s.zoneId = :zoneId AND s.moduleId = :moduleId AND s.status = true AND s.active = true")
    List<Store> findByZoneIdAndModuleIdAndActive(@Param("zoneId") Long zoneId, @Param("moduleId") Long moduleId);
    
    @Query("SELECT s FROM Store s WHERE s.featured = 1 AND s.status = true AND s.active = true")
    List<Store> findFeaturedStores();
    
    @Query("SELECT s FROM Store s WHERE s.name LIKE %:name% AND s.status = true AND s.active = true")
    List<Store> findByNameContainingIgnoreCase(@Param("name") String name);
    
    @Query("SELECT s FROM Store s WHERE s.vendorId = :vendorId")
    List<Store> findByVendorId(@Param("vendorId") Long vendorId);
    
    Optional<Store> findBySlug(String slug);
    
    @Query("SELECT COUNT(s) FROM Store s WHERE s.status = true AND s.active = true")
    Long countActiveStores();
    
    @Query("SELECT s FROM Store s WHERE " +
           "(:latitude IS NULL OR :longitude IS NULL OR " +
           "(6371 * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) * " +
           "cos(radians(s.longitude) - radians(:longitude)) + " +
           "sin(radians(:latitude)) * sin(radians(s.latitude)))) <= :radius) " +
           "AND s.status = true AND s.active = true")
    List<Store> findStoresWithinRadius(@Param("latitude") Double latitude, 
                                      @Param("longitude") Double longitude, 
                                      @Param("radius") Double radius);
}
