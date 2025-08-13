package com.sixammart.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CartRequest {
    @NotNull(message = "Item ID is required")
    private Long itemId;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    private String variations;
    private String addOns;
    private String itemType;

    // Constructors
    public CartRequest() {}

    public CartRequest(Long itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getVariations() { return variations; }
    public void setVariations(String variations) { this.variations = variations; }

    public String getAddOns() { return addOns; }
    public void setAddOns(String addOns) { this.addOns = addOns; }

    public String getItemType() { return itemType; }
    public void setItemType(String itemType) { this.itemType = itemType; }
}
