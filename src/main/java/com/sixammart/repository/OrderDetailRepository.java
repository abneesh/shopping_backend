package com.sixammart.repository;

import com.sixammart.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    
    @Query("SELECT od FROM OrderDetail od WHERE od.order.id = :orderId")
    List<OrderDetail> findByOrderId(@Param("orderId") Long orderId);
    
    @Query("SELECT od FROM OrderDetail od WHERE od.item.id = :itemId")
    List<OrderDetail> findByItemId(@Param("itemId") Long itemId);
    
    @Query("SELECT od FROM OrderDetail od WHERE od.order.user.id = :userId")
    List<OrderDetail> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT SUM(od.quantity * od.price) FROM OrderDetail od WHERE od.order.id = :orderId")
    Double getTotalAmountByOrderId(@Param("orderId") Long orderId);
    
    @Query("SELECT COUNT(od) FROM OrderDetail od WHERE od.item.id = :itemId")
    Long countByItemId(@Param("itemId") Long itemId);
}
