package com.sixammart.repository;

import com.sixammart.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    @Query("SELECT c FROM Category c WHERE c.status = true ORDER BY c.position ASC")
    List<Category> findAllActiveCategories();
    
    @Query("SELECT c FROM Category c WHERE c.parentId IS NULL AND c.status = true ORDER BY c.position ASC")
    List<Category> findMainCategories();
    
    @Query("SELECT c FROM Category c WHERE c.parentId = :parentId AND c.status = true ORDER BY c.position ASC")
    List<Category> findSubCategories(@Param("parentId") Long parentId);
    
    @Query("SELECT c FROM Category c WHERE c.moduleId = :moduleId AND c.status = true ORDER BY c.position ASC")
    List<Category> findByModuleIdAndActive(@Param("moduleId") Long moduleId);
    
    @Query("SELECT c FROM Category c WHERE c.moduleId = :moduleId AND c.parentId IS NULL AND c.status = true ORDER BY c.position ASC")
    List<Category> findMainCategoriesByModule(@Param("moduleId") Long moduleId);
    
    @Query("SELECT c FROM Category c WHERE c.featured = true AND c.status = true ORDER BY c.position ASC")
    List<Category> findFeaturedCategories();
    
    Optional<Category> findBySlug(String slug);
    
    @Query("SELECT c FROM Category c WHERE c.name LIKE %:name% AND c.status = true")
    List<Category> findByNameContainingIgnoreCase(@Param("name") String name);
    
    @Query("SELECT COUNT(c) FROM Category c WHERE c.status = true")
    Long countActiveCategories();
    
    @Query("SELECT c FROM Category c WHERE c.priority > 0 AND c.status = true ORDER BY c.priority DESC")
    List<Category> findPriorityCategories();
}
