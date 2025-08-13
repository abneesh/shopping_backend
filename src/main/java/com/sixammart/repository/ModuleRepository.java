package com.sixammart.repository;

import com.sixammart.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    
    @Query("SELECT m FROM Module m WHERE m.status = true ORDER BY m.id ASC")
    List<Module> findAllActiveModules();
    
    @Query("SELECT m FROM Module m WHERE m.moduleType = :moduleType AND m.status = true")
    List<Module> findByModuleTypeAndActive(@Param("moduleType") String moduleType);
    
    Optional<Module> findBySlug(String slug);
    
    @Query("SELECT m FROM Module m WHERE m.moduleName LIKE %:name% AND m.status = true")
    List<Module> findByModuleNameContainingIgnoreCase(@Param("name") String name);
    
    @Query("SELECT COUNT(m) FROM Module m WHERE m.status = true")
    Long countActiveModules();
    
    @Query("SELECT m FROM Module m WHERE m.allZoneService = true AND m.status = true")
    List<Module> findAllZoneServiceModules();
}
