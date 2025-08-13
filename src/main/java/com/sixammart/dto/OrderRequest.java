package com.sixammart.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;

public class OrderRequest {
    @NotNull(message = "Store ID is required")
    private Long storeId;

    @NotNull(message = "Order amount is required")
    @Positive(message = "Order amount must be positive")
    private Double orderAmount;

    private Double couponDiscountAmount = 0.0;
    private String couponDiscountTitle;
    private String paymentMethod;
    private Long deliveryAddressId;
    private String couponCode;
    private String orderNote;
    private String orderType = "delivery";
    private Double deliveryCharge = 0.0;
    private LocalDateTime scheduleAt;
    private String deliveryAddress;
    private Boolean scheduled = false;
    private Double dmTips = 0.0;
    private String orderAttachment;
    private Boolean cutlery = false;
    private String deliveryInstruction;

    @NotNull(message = "Order items are required")
    private List<OrderItemRequest> orderItems;

    // Constructors
    public OrderRequest() {}

    // Getters and Setters
    public Long getStoreId() { return storeId; }
    public void setStoreId(Long storeId) { this.storeId = storeId; }

    public Double getOrderAmount() { return orderAmount; }
    public void setOrderAmount(Double orderAmount) { this.orderAmount = orderAmount; }

    public Double getCouponDiscountAmount() { return couponDiscountAmount; }
    public void setCouponDiscountAmount(Double couponDiscountAmount) { this.couponDiscountAmount = couponDiscountAmount; }

    public String getCouponDiscountTitle() { return couponDiscountTitle; }
    public void setCouponDiscountTitle(String couponDiscountTitle) { this.couponDiscountTitle = couponDiscountTitle; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public Long getDeliveryAddressId() { return deliveryAddressId; }
    public void setDeliveryAddressId(Long deliveryAddressId) { this.deliveryAddressId = deliveryAddressId; }

    public String getCouponCode() { return couponCode; }
    public void setCouponCode(String couponCode) { this.couponCode = couponCode; }

    public String getOrderNote() { return orderNote; }
    public void setOrderNote(String orderNote) { this.orderNote = orderNote; }

    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }

    public Double getDeliveryCharge() { return deliveryCharge; }
    public void setDeliveryCharge(Double deliveryCharge) { this.deliveryCharge = deliveryCharge; }

    public LocalDateTime getScheduleAt() { return scheduleAt; }
    public void setScheduleAt(LocalDateTime scheduleAt) { this.scheduleAt = scheduleAt; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public Boolean getScheduled() { return scheduled; }
    public void setScheduled(Boolean scheduled) { this.scheduled = scheduled; }

    public Double getDmTips() { return dmTips; }
    public void setDmTips(Double dmTips) { this.dmTips = dmTips; }

    public String getOrderAttachment() { return orderAttachment; }
    public void setOrderAttachment(String orderAttachment) { this.orderAttachment = orderAttachment; }

    public Boolean getCutlery() { return cutlery; }
    public void setCutlery(Boolean cutlery) { this.cutlery = cutlery; }

    public String getDeliveryInstruction() { return deliveryInstruction; }
    public void setDeliveryInstruction(String deliveryInstruction) { this.deliveryInstruction = deliveryInstruction; }

    public List<OrderItemRequest> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemRequest> orderItems) { this.orderItems = orderItems; }

    // Inner class for order items
    public static class OrderItemRequest {
        @NotNull(message = "Item ID is required")
        private Long itemId;

        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity must be positive")
        private Integer quantity;

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        private Double price;

        private String variations;
        private String addOns;
        private Double discountOnItem = 0.0;
        private String discountType = "amount";
        private Double taxAmount = 0.0;
        private String variant;
        private Double totalAddOnPrice = 0.0;

        // Constructors
        public OrderItemRequest() {}

        // Getters and Setters
        public Long getItemId() { return itemId; }
        public void setItemId(Long itemId) { this.itemId = itemId; }

        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }

        public Double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }

        public String getVariations() { return variations; }
        public void setVariations(String variations) { this.variations = variations; }

        public String getAddOns() { return addOns; }
        public void setAddOns(String addOns) { this.addOns = addOns; }

        public Double getDiscountOnItem() { return discountOnItem; }
        public void setDiscountOnItem(Double discountOnItem) { this.discountOnItem = discountOnItem; }

        public String getDiscountType() { return discountType; }
        public void setDiscountType(String discountType) { this.discountType = discountType; }

        public Double getTaxAmount() { return taxAmount; }
        public void setTaxAmount(Double taxAmount) { this.taxAmount = taxAmount; }

        public String getVariant() { return variant; }
        public void setVariant(String variant) { this.variant = variant; }

        public Double getTotalAddOnPrice() { return totalAddOnPrice; }
        public void setTotalAddOnPrice(Double totalAddOnPrice) { this.totalAddOnPrice = totalAddOnPrice; }
    }
}
