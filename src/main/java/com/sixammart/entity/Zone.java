package com.sixammart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "zones")
@EntityListeners(AuditingEntityListener.class)
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "coordinates", columnDefinition = "TEXT")
    private String coordinates;

    @Column(name = "status", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status = true;

    @Column(name = "cash_on_delivery", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean cashOnDelivery = true;

    @Column(name = "digital_payment", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean digitalPayment = true;

    @Column(name = "minimum_shipping_charge", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double minimumShippingCharge = 0.0;

    @Column(name = "per_km_shipping_charge", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double perKmShippingCharge = 0.0;

    @Column(name = "maximum_shipping_charge", columnDefinition = "DECIMAL(10,2)")
    private Double maximumShippingCharge;

    @Column(name = "maximum_cod_order_amount", columnDefinition = "DECIMAL(10,2)")
    private Double maximumCodOrderAmount;

    @Column(name = "increased_delivery_fee", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double increasedDeliveryFee = 0.0;

    @Column(name = "increased_delivery_fee_status", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean increasedDeliveryFeeStatus = false;

    @Column(name = "increase_delivery_charge_message")
    private String increaseDeliveryChargeMessage;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Zone() {}

    public Zone(String name, String coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCoordinates() { return coordinates; }
    public void setCoordinates(String coordinates) { this.coordinates = coordinates; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public Boolean getCashOnDelivery() { return cashOnDelivery; }
    public void setCashOnDelivery(Boolean cashOnDelivery) { this.cashOnDelivery = cashOnDelivery; }

    public Boolean getDigitalPayment() { return digitalPayment; }
    public void setDigitalPayment(Boolean digitalPayment) { this.digitalPayment = digitalPayment; }

    public Double getMinimumShippingCharge() { return minimumShippingCharge; }
    public void setMinimumShippingCharge(Double minimumShippingCharge) { this.minimumShippingCharge = minimumShippingCharge; }

    public Double getPerKmShippingCharge() { return perKmShippingCharge; }
    public void setPerKmShippingCharge(Double perKmShippingCharge) { this.perKmShippingCharge = perKmShippingCharge; }

    public Double getMaximumShippingCharge() { return maximumShippingCharge; }
    public void setMaximumShippingCharge(Double maximumShippingCharge) { this.maximumShippingCharge = maximumShippingCharge; }

    public Double getMaximumCodOrderAmount() { return maximumCodOrderAmount; }
    public void setMaximumCodOrderAmount(Double maximumCodOrderAmount) { this.maximumCodOrderAmount = maximumCodOrderAmount; }

    public Double getIncreasedDeliveryFee() { return increasedDeliveryFee; }
    public void setIncreasedDeliveryFee(Double increasedDeliveryFee) { this.increasedDeliveryFee = increasedDeliveryFee; }

    public Boolean getIncreasedDeliveryFeeStatus() { return increasedDeliveryFeeStatus; }
    public void setIncreasedDeliveryFeeStatus(Boolean increasedDeliveryFeeStatus) { this.increasedDeliveryFeeStatus = increasedDeliveryFeeStatus; }

    public String getIncreaseDeliveryChargeMessage() { return increaseDeliveryChargeMessage; }
    public void setIncreaseDeliveryChargeMessage(String increaseDeliveryChargeMessage) { this.increaseDeliveryChargeMessage = increaseDeliveryChargeMessage; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
