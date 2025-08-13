package com.sixammart.controller;

import com.sixammart.dto.ApiResponse;
import com.sixammart.dto.OrderRequest;
import com.sixammart.entity.Order;
import com.sixammart.security.UserPrincipal;
import com.sixammart.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer/order")
@Tag(name = "Orders", description = "Order management APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    @Operation(summary = "Get user orders")
    public ResponseEntity<ApiResponse<List<Order>>> getUserOrders(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            int page = offset / limit;
            Page<Order> orderPage = orderService.getOrdersByUser(userPrincipal.getId(), page, limit);
            
            return ResponseEntity.ok(ApiResponse.success(
                "Orders retrieved successfully", 
                orderPage.getContent(),
                (int) orderPage.getTotalElements(),
                limit,
                offset
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID")
    public ResponseEntity<ApiResponse<Order>> getOrderById(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long id) {
        try {
            Order order = orderService.getOrderById(id);
            
            // Check if order belongs to the user
            if (!order.getUser().getId().equals(userPrincipal.getId())) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Unauthorized access to order"));
            }
            
            return ResponseEntity.ok(ApiResponse.success("Order retrieved successfully", order));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/place")
    @Operation(summary = "Place a new order")
    public ResponseEntity<ApiResponse<Order>> placeOrder(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody OrderRequest request) {
        try {
            Order order = orderService.createOrder(userPrincipal.getId(), request);
            return ResponseEntity.ok(ApiResponse.success("Order placed successfully", order));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{id}/cancel")
    @Operation(summary = "Cancel order")
    public ResponseEntity<ApiResponse<Order>> cancelOrder(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long id,
            @RequestParam(required = false) String reason) {
        try {
            Order order = orderService.getOrderById(id);
            
            // Check if order belongs to the user
            if (!order.getUser().getId().equals(userPrincipal.getId())) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Unauthorized access to order"));
            }
            
            // Check if order can be canceled
            if (!order.getOrderStatus().equals("pending") && !order.getOrderStatus().equals("confirmed")) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Order cannot be canceled at this stage"));
            }
            
            Order updatedOrder = orderService.updateOrderStatus(id, "canceled");
            return ResponseEntity.ok(ApiResponse.success("Order canceled successfully", updatedOrder));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/track/{id}")
    @Operation(summary = "Track order")
    public ResponseEntity<ApiResponse<Map<String, Object>>> trackOrder(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long id) {
        try {
            Order order = orderService.getOrderById(id);
            
            // Check if order belongs to the user
            if (!order.getUser().getId().equals(userPrincipal.getId())) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Unauthorized access to order"));
            }
            
            Map<String, Object> trackingInfo = Map.of(
                "order_id", order.getId(),
                "order_status", order.getOrderStatus(),
                "payment_status", order.getPaymentStatus(),
                "created_at", order.getCreatedAt(),
                "confirmed", order.getConfirmed(),
                "processing", order.getProcessing(),
                "out_for_delivery", order.getHandover(),
                "delivered", order.getDelivered(),
                "delivery_man", order.getDeliveryManId() != null ? 
                    Map.of("id", order.getDeliveryManId(), "name", "Delivery Person") : null
            );
            
            return ResponseEntity.ok(ApiResponse.success("Order tracking info retrieved successfully", trackingInfo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/running")
    @Operation(summary = "Get running orders")
    public ResponseEntity<ApiResponse<List<Order>>> getRunningOrders(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        try {
            List<Order> orders = orderService.getOrdersByUser(userPrincipal.getId());
            
            // Filter running orders
            List<Order> runningOrders = orders.stream()
                .filter(order -> !order.getOrderStatus().equals("delivered") && 
                               !order.getOrderStatus().equals("canceled") &&
                               !order.getOrderStatus().equals("refunded"))
                .toList();
            
            return ResponseEntity.ok(ApiResponse.success("Running orders retrieved successfully", runningOrders));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/history")
    @Operation(summary = "Get order history")
    public ResponseEntity<ApiResponse<List<Order>>> getOrderHistory(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            List<Order> orders = orderService.getOrdersByUser(userPrincipal.getId());
            
            // Filter completed orders
            List<Order> historyOrders = orders.stream()
                .filter(order -> order.getOrderStatus().equals("delivered") || 
                               order.getOrderStatus().equals("canceled") ||
                               order.getOrderStatus().equals("refunded"))
                .toList();
            
            // Apply pagination
            int total = historyOrders.size();
            int fromIndex = Math.min(offset, total);
            int toIndex = Math.min(offset + limit, total);
            List<Order> paginatedOrders = historyOrders.subList(fromIndex, toIndex);
            
            return ResponseEntity.ok(ApiResponse.success(
                "Order history retrieved successfully", 
                paginatedOrders,
                total,
                limit,
                offset
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/{id}/review")
    @Operation(summary = "Submit order review")
    public ResponseEntity<ApiResponse<String>> submitOrderReview(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long id,
            @RequestParam Double rating,
            @RequestParam(required = false) String comment) {
        try {
            Order order = orderService.getOrderById(id);
            
            // Check if order belongs to the user
            if (!order.getUser().getId().equals(userPrincipal.getId())) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Unauthorized access to order"));
            }
            
            // Check if order is delivered
            if (!order.getOrderStatus().equals("delivered")) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Can only review delivered orders"));
            }
            
            // In a real implementation, save the review to a reviews table
            return ResponseEntity.ok(ApiResponse.success("Review submitted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}
