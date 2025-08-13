package com.sixammart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "order_amount", columnDefinition = "DECIMAL(10,2)")
    private Double orderAmount;

    @Column(name = "coupon_discount_amount", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double couponDiscountAmount = 0.0;

    @Column(name = "coupon_discount_title")
    private String couponDiscountTitle;

    @Column(name = "payment_status", columnDefinition = "VARCHAR(20) DEFAULT 'unpaid'")
    private String paymentStatus = "unpaid";

    @Column(name = "order_status", columnDefinition = "VARCHAR(20) DEFAULT 'pending'")
    private String orderStatus = "pending";

    @Column(name = "total_tax_amount", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double totalTaxAmount = 0.0;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "transaction_reference")
    private String transactionReference;

    @Column(name = "delivery_address_id")
    private Long deliveryAddressId;

    @Column(name = "delivery_man_id")
    private Long deliveryManId;

    @Column(name = "coupon_code")
    private String couponCode;

    @Column(name = "order_note")
    private String orderNote;

    @Column(name = "order_type", columnDefinition = "VARCHAR(20) DEFAULT 'delivery'")
    private String orderType = "delivery";

    @Column(name = "checked", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean checked = false;

    @Column(name = "store_discount_amount", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double storeDiscountAmount = 0.0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "delivery_charge", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double deliveryCharge = 0.0;

    @Column(name = "schedule_at")
    private LocalDateTime scheduleAt;

    @Column(name = "callback")
    private String callback;

    @Column(name = "otp")
    private String otp;

    @Column(name = "pending")
    private LocalDateTime pending;

    @Column(name = "accepted")
    private LocalDateTime accepted;

    @Column(name = "confirmed")
    private LocalDateTime confirmed;

    @Column(name = "processing")
    private LocalDateTime processing;

    @Column(name = "handover")
    private LocalDateTime handover;

    @Column(name = "picked_up")
    private LocalDateTime pickedUp;

    @Column(name = "delivered")
    private LocalDateTime delivered;

    @Column(name = "canceled")
    private LocalDateTime canceled;

    @Column(name = "refund_requested")
    private LocalDateTime refundRequested;

    @Column(name = "refunded")
    private LocalDateTime refunded;

    @Column(name = "delivery_address", columnDefinition = "TEXT")
    private String deliveryAddress;

    @Column(name = "scheduled", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean scheduled = false;

    @Column(name = "store_discount_amount_type")
    private String storeDiscountAmountType;

    @Column(name = "original_delivery_charge", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double originalDeliveryCharge = 0.0;

    @Column(name = "failed")
    private LocalDateTime failed;

    @Column(name = "adjusment", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double adjustment = 0.0;

    @Column(name = "edited", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean edited = false;

    @Column(name = "delivery_verification_code")
    private String deliveryVerificationCode;

    @Column(name = "dm_tips", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double dmTips = 0.0;

    @Column(name = "zone_id")
    private Long zoneId;

    @Column(name = "module_id")
    private Long moduleId;

    @Column(name = "order_attachment")
    private String orderAttachment;

    @Column(name = "order_proof")
    private String orderProof;

    @Column(name = "distance", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double distance = 0.0;

    @Column(name = "prescription_order", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean prescriptionOrder = false;

    @Column(name = "cutlery", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean cutlery = false;

    @Column(name = "unavailable_item_note")
    private String unavailableItemNote;

    @Column(name = "delivery_instruction")
    private String deliveryInstruction;

    @Column(name = "partially_paid_amount", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double partiallyPaidAmount = 0.0;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

    @CreatedDate
    @Column(name = "created_at_audit", updatable = false)
    private LocalDateTime createdAtAudit;

    @LastModifiedDate
    @Column(name = "updated_at_audit")
    private LocalDateTime updatedAtAudit;

    // Constructors
    public Order() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Store getStore() { return store; }
    public void setStore(Store store) { this.store = store; }

    public Double getOrderAmount() { return orderAmount; }
    public void setOrderAmount(Double orderAmount) { this.orderAmount = orderAmount; }

    public Double getCouponDiscountAmount() { return couponDiscountAmount; }
    public void setCouponDiscountAmount(Double couponDiscountAmount) { this.couponDiscountAmount = couponDiscountAmount; }

    public String getCouponDiscountTitle() { return couponDiscountTitle; }
    public void setCouponDiscountTitle(String couponDiscountTitle) { this.couponDiscountTitle = couponDiscountTitle; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public Double getTotalTaxAmount() { return totalTaxAmount; }
    public void setTotalTaxAmount(Double totalTaxAmount) { this.totalTaxAmount = totalTaxAmount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getTransactionReference() { return transactionReference; }
    public void setTransactionReference(String transactionReference) { this.transactionReference = transactionReference; }

    public Long getDeliveryAddressId() { return deliveryAddressId; }
    public void setDeliveryAddressId(Long deliveryAddressId) { this.deliveryAddressId = deliveryAddressId; }

    public Long getDeliveryManId() { return deliveryManId; }
    public void setDeliveryManId(Long deliveryManId) { this.deliveryManId = deliveryManId; }

    public String getCouponCode() { return couponCode; }
    public void setCouponCode(String couponCode) { this.couponCode = couponCode; }

    public String getOrderNote() { return orderNote; }
    public void setOrderNote(String orderNote) { this.orderNote = orderNote; }

    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }

    public Boolean getChecked() { return checked; }
    public void setChecked(Boolean checked) { this.checked = checked; }

    public Double getStoreDiscountAmount() { return storeDiscountAmount; }
    public void setStoreDiscountAmount(Double storeDiscountAmount) { this.storeDiscountAmount = storeDiscountAmount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Double getDeliveryCharge() { return deliveryCharge; }
    public void setDeliveryCharge(Double deliveryCharge) { this.deliveryCharge = deliveryCharge; }

    public LocalDateTime getScheduleAt() { return scheduleAt; }
    public void setScheduleAt(LocalDateTime scheduleAt) { this.scheduleAt = scheduleAt; }

    public String getCallback() { return callback; }
    public void setCallback(String callback) { this.callback = callback; }

    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }

    public LocalDateTime getPending() { return pending; }
    public void setPending(LocalDateTime pending) { this.pending = pending; }

    public LocalDateTime getAccepted() { return accepted; }
    public void setAccepted(LocalDateTime accepted) { this.accepted = accepted; }

    public LocalDateTime getConfirmed() { return confirmed; }
    public void setConfirmed(LocalDateTime confirmed) { this.confirmed = confirmed; }

    public LocalDateTime getProcessing() { return processing; }
    public void setProcessing(LocalDateTime processing) { this.processing = processing; }

    public LocalDateTime getHandover() { return handover; }
    public void setHandover(LocalDateTime handover) { this.handover = handover; }

    public LocalDateTime getPickedUp() { return pickedUp; }
    public void setPickedUp(LocalDateTime pickedUp) { this.pickedUp = pickedUp; }

    public LocalDateTime getDelivered() { return delivered; }
    public void setDelivered(LocalDateTime delivered) { this.delivered = delivered; }

    public LocalDateTime getCanceled() { return canceled; }
    public void setCanceled(LocalDateTime canceled) { this.canceled = canceled; }

    public LocalDateTime getRefundRequested() { return refundRequested; }
    public void setRefundRequested(LocalDateTime refundRequested) { this.refundRequested = refundRequested; }

    public LocalDateTime getRefunded() { return refunded; }
    public void setRefunded(LocalDateTime refunded) { this.refunded = refunded; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public Boolean getScheduled() { return scheduled; }
    public void setScheduled(Boolean scheduled) { this.scheduled = scheduled; }

    public String getStoreDiscountAmountType() { return storeDiscountAmountType; }
    public void setStoreDiscountAmountType(String storeDiscountAmountType) { this.storeDiscountAmountType = storeDiscountAmountType; }

    public Double getOriginalDeliveryCharge() { return originalDeliveryCharge; }
    public void setOriginalDeliveryCharge(Double originalDeliveryCharge) { this.originalDeliveryCharge = originalDeliveryCharge; }

    public LocalDateTime getFailed() { return failed; }
    public void setFailed(LocalDateTime failed) { this.failed = failed; }

    public Double getAdjustment() { return adjustment; }
    public void setAdjustment(Double adjustment) { this.adjustment = adjustment; }

    public Boolean getEdited() { return edited; }
    public void setEdited(Boolean edited) { this.edited = edited; }

    public String getDeliveryVerificationCode() { return deliveryVerificationCode; }
    public void setDeliveryVerificationCode(String deliveryVerificationCode) { this.deliveryVerificationCode = deliveryVerificationCode; }

    public Double getDmTips() { return dmTips; }
    public void setDmTips(Double dmTips) { this.dmTips = dmTips; }

    public Long getZoneId() { return zoneId; }
    public void setZoneId(Long zoneId) { this.zoneId = zoneId; }

    public Long getModuleId() { return moduleId; }
    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }

    public String getOrderAttachment() { return orderAttachment; }
    public void setOrderAttachment(String orderAttachment) { this.orderAttachment = orderAttachment; }

    public String getOrderProof() { return orderProof; }
    public void setOrderProof(String orderProof) { this.orderProof = orderProof; }

    public Double getDistance() { return distance; }
    public void setDistance(Double distance) { this.distance = distance; }

    public Boolean getPrescriptionOrder() { return prescriptionOrder; }
    public void setPrescriptionOrder(Boolean prescriptionOrder) { this.prescriptionOrder = prescriptionOrder; }

    public Boolean getCutlery() { return cutlery; }
    public void setCutlery(Boolean cutlery) { this.cutlery = cutlery; }

    public String getUnavailableItemNote() { return unavailableItemNote; }
    public void setUnavailableItemNote(String unavailableItemNote) { this.unavailableItemNote = unavailableItemNote; }

    public String getDeliveryInstruction() { return deliveryInstruction; }
    public void setDeliveryInstruction(String deliveryInstruction) { this.deliveryInstruction = deliveryInstruction; }

    public Double getPartiallyPaidAmount() { return partiallyPaidAmount; }
    public void setPartiallyPaidAmount(Double partiallyPaidAmount) { this.partiallyPaidAmount = partiallyPaidAmount; }

    public List<OrderDetail> getOrderDetails() { return orderDetails; }
    public void setOrderDetails(List<OrderDetail> orderDetails) { this.orderDetails = orderDetails; }

    public LocalDateTime getCreatedAtAudit() { return createdAtAudit; }
    public void setCreatedAtAudit(LocalDateTime createdAtAudit) { this.createdAtAudit = createdAtAudit; }

    public LocalDateTime getUpdatedAtAudit() { return updatedAtAudit; }
    public void setUpdatedAtAudit(LocalDateTime updatedAtAudit) { this.updatedAtAudit = updatedAtAudit; }
}
