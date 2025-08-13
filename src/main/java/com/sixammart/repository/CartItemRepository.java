package com.sixammart.repository;

import com.sixammart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
    @Query("SELECT c FROM CartItem c WHERE c.user.id = :userId ORDER BY c.createdAt DESC")
    List<CartItem> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT c FROM CartItem c WHERE c.user.id = :userId AND c.item.id = :itemId")
    Optional<CartItem> findByUserIdAndItemId(@Param("userId") Long userId, @Param("itemId") Long itemId);
    
    @Query("SELECT c FROM CartItem c WHERE c.user.id = :userId AND c.item.store.id = :storeId")
    List<CartItem> findByUserIdAndStoreId(@Param("userId") Long userId, @Param("storeId") Long storeId);
    
    @Query("SELECT COUNT(c) FROM CartItem c WHERE c.user.id = :userId")
    Long countByUserId(@Param("userId") Long userId);
    
    @Query("SELECT SUM(c.quantity * c.price) FROM CartItem c WHERE c.user.id = :userId")
    Double getTotalAmountByUserId(@Param("userId") Long userId);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.user.id = :userId AND c.item.store.id = :storeId")
    void deleteByUserIdAndStoreId(@Param("userId") Long userId, @Param("storeId") Long storeId);
    
    @Query("SELECT DISTINCT c.item.store.id FROM CartItem c WHERE c.user.id = :userId")
    List<Long> findDistinctStoreIdsByUserId(@Param("userId") Long userId);
}
