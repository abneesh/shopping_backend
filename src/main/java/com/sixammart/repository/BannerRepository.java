package com.sixammart.repository;

import com.sixammart.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
    
    @Query("SELECT b FROM Banner b WHERE b.status = true ORDER BY b.id DESC")
    List<Banner> findAllActiveBanners();
    
    @Query("SELECT b FROM Banner b WHERE b.moduleId = :moduleId AND b.status = true ORDER BY b.id DESC")
    List<Banner> findByModuleIdAndActive(@Param("moduleId") Long moduleId);
    
    @Query("SELECT b FROM Banner b WHERE b.zoneId = :zoneId AND b.status = true ORDER BY b.id DESC")
    List<Banner> findByZoneIdAndActive(@Param("zoneId") Long zoneId);
    
    @Query("SELECT b FROM Banner b WHERE b.moduleId = :moduleId AND b.zoneId = :zoneId AND b.status = true ORDER BY b.id DESC")
    List<Banner> findByModuleIdAndZoneIdAndActive(@Param("moduleId") Long moduleId, @Param("zoneId") Long zoneId);
    
    @Query("SELECT b FROM Banner b WHERE b.featured = true AND b.status = true ORDER BY b.id DESC")
    List<Banner> findFeaturedBanners();
    
    @Query("SELECT b FROM Banner b WHERE b.type = :type AND b.status = true ORDER BY b.id DESC")
    List<Banner> findByTypeAndActive(@Param("type") String type);
    
    @Query("SELECT COUNT(b) FROM Banner b WHERE b.status = true")
    Long countActiveBanners();
}
