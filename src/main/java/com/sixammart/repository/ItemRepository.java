package com.sixammart.repository;

import com.sixammart.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
    @Query("SELECT i FROM Item i WHERE i.status = true")
    List<Item> findAllActiveItems();
    
    @Query("SELECT i FROM Item i WHERE i.status = true")
    Page<Item> findAllActiveItems(Pageable pageable);
    
    @Query("SELECT i FROM Item i WHERE i.store.id = :storeId AND i.status = true")
    List<Item> findByStoreIdAndActive(@Param("storeId") Long storeId);
    
    @Query("SELECT i FROM Item i WHERE i.categoryId = :categoryId AND i.status = true")
    List<Item> findByCategoryIdAndActive(@Param("categoryId") Long categoryId);
    
    @Query("SELECT i FROM Item i WHERE i.store.id = :storeId AND i.categoryId = :categoryId AND i.status = true")
    List<Item> findByStoreIdAndCategoryIdAndActive(@Param("storeId") Long storeId, @Param("categoryId") Long categoryId);
    
    @Query("SELECT i FROM Item i WHERE i.recommended = true AND i.status = true")
    List<Item> findRecommendedItems();
    
    @Query("SELECT i FROM Item i WHERE i.organic = true AND i.status = true")
    List<Item> findOrganicItems();
    
    @Query("SELECT i FROM Item i WHERE i.name LIKE %:name% AND i.status = true")
    List<Item> findByNameContainingIgnoreCase(@Param("name") String name);
    
    @Query("SELECT i FROM Item i WHERE i.veg = :veg AND i.status = true")
    List<Item> findByVegAndActive(@Param("veg") Integer veg);
    
    Optional<Item> findBySlug(String slug);
    
    @Query("SELECT i FROM Item i WHERE i.store.id = :storeId AND i.status = true ORDER BY i.orderCount DESC")
    List<Item> findPopularItemsByStore(@Param("storeId") Long storeId, Pageable pageable);
    
    @Query("SELECT i FROM Item i WHERE i.status = true ORDER BY i.orderCount DESC")
    List<Item> findPopularItems(Pageable pageable);
    
    @Query("SELECT i FROM Item i WHERE i.status = true ORDER BY i.avgRating DESC")
    List<Item> findTopRatedItems(Pageable pageable);
    
    @Query("SELECT i FROM Item i WHERE i.discount > 0 AND i.status = true")
    List<Item> findDiscountedItems();
    
    @Query("SELECT COUNT(i) FROM Item i WHERE i.status = true")
    Long countActiveItems();
    
    @Query("SELECT i FROM Item i WHERE i.store.zoneId = :zoneId AND i.status = true")
    List<Item> findByZoneIdAndActive(@Param("zoneId") Long zoneId);
}
