package com.sixammart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_details")
@EntityListeners(AuditingEntityListener.class)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @NotNull
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price", columnDefinition = "DECIMAL(10,2)")
    private Double price;

    @Column(name = "variations", columnDefinition = "TEXT")
    private String variations;

    @Column(name = "add_ons", columnDefinition = "TEXT")
    private String addOns;

    @Column(name = "discount_on_item", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double discountOnItem = 0.0;

    @Column(name = "discount_type", columnDefinition = "VARCHAR(20) DEFAULT 'amount'")
    private String discountType = "amount";

    @Column(name = "item_details", columnDefinition = "TEXT")
    private String itemDetails;

    @Column(name = "tax_amount", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double taxAmount = 0.0;

    @Column(name = "variant")
    private String variant;

    @Column(name = "total_add_on_price", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double totalAddOnPrice = 0.0;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public OrderDetail() {}

    public OrderDetail(Order order, Item item, Integer quantity, Double price) {
        this.order = order;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

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

    public String getItemDetails() { return itemDetails; }
    public void setItemDetails(String itemDetails) { this.itemDetails = itemDetails; }

    public Double getTaxAmount() { return taxAmount; }
    public void setTaxAmount(Double taxAmount) { this.taxAmount = taxAmount; }

    public String getVariant() { return variant; }
    public void setVariant(String variant) { this.variant = variant; }

    public Double getTotalAddOnPrice() { return totalAddOnPrice; }
    public void setTotalAddOnPrice(Double totalAddOnPrice) { this.totalAddOnPrice = totalAddOnPrice; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
