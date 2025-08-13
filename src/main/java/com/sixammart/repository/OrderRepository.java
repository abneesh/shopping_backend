package com.sixammart.repository;

import com.sixammart.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId ORDER BY o.createdAt DESC")
    List<Order> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId ORDER BY o.createdAt DESC")
    Page<Order> findByUserId(@Param("userId") Long userId, Pageable pageable);
    
    @Query("SELECT o FROM Order o WHERE o.store.id = :storeId ORDER BY o.createdAt DESC")
    List<Order> findByStoreId(@Param("storeId") Long storeId);
    
    @Query("SELECT o FROM Order o WHERE o.orderStatus = :status ORDER BY o.createdAt DESC")
    List<Order> findByOrderStatus(@Param("status") String status);
    
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.orderStatus = :status ORDER BY o.createdAt DESC")
    List<Order> findByUserIdAndOrderStatus(@Param("userId") Long userId, @Param("status") String status);
    
    @Query("SELECT o FROM Order o WHERE o.paymentStatus = :paymentStatus ORDER BY o.createdAt DESC")
    List<Order> findByPaymentStatus(@Param("paymentStatus") String paymentStatus);
    
    @Query("SELECT o FROM Order o WHERE o.createdAt BETWEEN :startDate AND :endDate ORDER BY o.createdAt DESC")
    List<Order> findOrdersBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT o FROM Order o WHERE o.zoneId = :zoneId ORDER BY o.createdAt DESC")
    List<Order> findByZoneId(@Param("zoneId") Long zoneId);
    
    @Query("SELECT o FROM Order o WHERE o.moduleId = :moduleId ORDER BY o.createdAt DESC")
    List<Order> findByModuleId(@Param("moduleId") Long moduleId);
    
    @Query("SELECT COUNT(o) FROM Order o WHERE o.user.id = :userId")
    Long countByUserId(@Param("userId") Long userId);
    
    @Query("SELECT COUNT(o) FROM Order o WHERE o.store.id = :storeId")
    Long countByStoreId(@Param("storeId") Long storeId);
    
    @Query("SELECT SUM(o.orderAmount) FROM Order o WHERE o.store.id = :storeId AND o.orderStatus = 'delivered'")
    Double getTotalRevenueByStore(@Param("storeId") Long storeId);
    
    @Query("SELECT SUM(o.orderAmount) FROM Order o WHERE o.orderStatus = 'delivered'")
    Double getTotalRevenue();
    
    @Query("SELECT o FROM Order o WHERE o.orderStatus IN ('pending', 'confirmed', 'processing', 'out_for_delivery') ORDER BY o.createdAt DESC")
    List<Order> findActiveOrders();
    
    @Query("SELECT o FROM Order o WHERE o.scheduled = true AND o.scheduleAt <= :currentTime AND o.orderStatus = 'pending'")
    List<Order> findScheduledOrdersToProcess(@Param("currentTime") LocalDateTime currentTime);
}
