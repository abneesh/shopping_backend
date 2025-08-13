package com.sixammart.service;

import com.sixammart.dto.OrderRequest;
import com.sixammart.entity.*;
import com.sixammart.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Page<Order> getOrdersByUser(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findByUserId(userId, pageable);
    }

    public List<Order> getOrdersByStore(Long storeId) {
        return orderRepository.findByStoreId(storeId);
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByOrderStatus(status);
    }

    public List<Order> getActiveOrders() {
        return orderRepository.findActiveOrders();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public Order createOrder(Long userId, OrderRequest request) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        Store store = storeRepository.findById(request.getStoreId())
            .orElseThrow(() -> new RuntimeException("Store not found with id: " + request.getStoreId()));

        // Create order
        Order order = new Order();
        order.setUser(user);
        order.setStore(store);
        order.setOrderAmount(request.getOrderAmount());
        order.setCouponDiscountAmount(request.getCouponDiscountAmount());
        order.setCouponDiscountTitle(request.getCouponDiscountTitle());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setDeliveryAddressId(request.getDeliveryAddressId());
        order.setCouponCode(request.getCouponCode());
        order.setOrderNote(request.getOrderNote());
        order.setOrderType(request.getOrderType());
        order.setDeliveryCharge(request.getDeliveryCharge());
        order.setScheduleAt(request.getScheduleAt());
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setScheduled(request.getScheduled());
        order.setDmTips(request.getDmTips());
        order.setOrderAttachment(request.getOrderAttachment());
        order.setCutlery(request.getCutlery());
        order.setDeliveryInstruction(request.getDeliveryInstruction());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        order.setPending(LocalDateTime.now());
        order.setOtp(generateOTP());
        order.setDeliveryVerificationCode(generateVerificationCode());

        // Save order first to get ID
        order = orderRepository.save(order);

        // Create order details
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderRequest.OrderItemRequest itemRequest : request.getOrderItems()) {
            Item item = itemRepository.findById(itemRequest.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + itemRequest.getItemId()));

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setItem(item);
            orderDetail.setQuantity(itemRequest.getQuantity());
            orderDetail.setPrice(itemRequest.getPrice());
            orderDetail.setVariations(itemRequest.getVariations());
            orderDetail.setAddOns(itemRequest.getAddOns());
            orderDetail.setDiscountOnItem(itemRequest.getDiscountOnItem());
            orderDetail.setDiscountType(itemRequest.getDiscountType());
            orderDetail.setTaxAmount(itemRequest.getTaxAmount());
            orderDetail.setVariant(itemRequest.getVariant());
            orderDetail.setTotalAddOnPrice(itemRequest.getTotalAddOnPrice());
            
            orderDetails.add(orderDetail);
        }

        order.setOrderDetails(orderDetails);
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = getOrderById(orderId);
        order.setOrderStatus(status);
        order.setUpdatedAt(LocalDateTime.now());

        // Set status timestamps
        LocalDateTime now = LocalDateTime.now();
        switch (status.toLowerCase()) {
            case "confirmed":
                order.setConfirmed(now);
                break;
            case "processing":
                order.setProcessing(now);
                break;
            case "out_for_delivery":
                order.setHandover(now);
                break;
            case "delivered":
                order.setDelivered(now);
                break;
            case "canceled":
                order.setCanceled(now);
                break;
            case "refund_requested":
                order.setRefundRequested(now);
                break;
            case "refunded":
                order.setRefunded(now);
                break;
            case "failed":
                order.setFailed(now);
                break;
        }

        return orderRepository.save(order);
    }

    public Order updatePaymentStatus(Long orderId, String paymentStatus) {
        Order order = getOrderById(orderId);
        order.setPaymentStatus(paymentStatus);
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Long getOrdersCountByUser(Long userId) {
        return orderRepository.countByUserId(userId);
    }

    public Long getOrdersCountByStore(Long storeId) {
        return orderRepository.countByStoreId(storeId);
    }

    public Double getTotalRevenueByStore(Long storeId) {
        Double revenue = orderRepository.getTotalRevenueByStore(storeId);
        return revenue != null ? revenue : 0.0;
    }

    public Double getTotalRevenue() {
        Double revenue = orderRepository.getTotalRevenue();
        return revenue != null ? revenue : 0.0;
    }

    public List<Order> getOrdersBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findOrdersBetweenDates(startDate, endDate);
    }

    public List<Order> getScheduledOrdersToProcess() {
        return orderRepository.findScheduledOrdersToProcess(LocalDateTime.now());
    }

    private String generateOTP() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }

    private String generateVerificationCode() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
