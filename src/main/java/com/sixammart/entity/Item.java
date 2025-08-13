package com.sixammart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "items")
@EntityListeners(AuditingEntityListener.class)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "image_full_url")
    private String imageFullUrl;

    @NotNull
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_ids", columnDefinition = "TEXT")
    private String categoryIds;

    @Column(name = "variations", columnDefinition = "TEXT")
    private String variations;

    @Column(name = "add_ons", columnDefinition = "TEXT")
    private String addOns;

    @Column(name = "attributes", columnDefinition = "TEXT")
    private String attributes;

    @Column(name = "choice_options", columnDefinition = "TEXT")
    private String choiceOptions;

    @NotNull
    @Column(name = "price", columnDefinition = "DECIMAL(10,2)")
    private Double price;

    @Column(name = "tax", columnDefinition = "DECIMAL(5,2) DEFAULT 0.00")
    private Double tax = 0.0;

    @Column(name = "tax_type", columnDefinition = "VARCHAR(20) DEFAULT 'percent'")
    private String taxType = "percent";

    @Column(name = "discount", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double discount = 0.0;

    @Column(name = "discount_type", columnDefinition = "VARCHAR(20) DEFAULT 'percent'")
    private String discountType = "percent";

    @Column(name = "available_time_starts")
    private LocalTime availableTimeStarts;

    @Column(name = "available_time_ends")
    private LocalTime availableTimeEnds;

    @Column(name = "veg", columnDefinition = "INT DEFAULT 0")
    private Integer veg = 0;

    @Column(name = "status", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status = true;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "unit_id")
    private Long unitId;

    @Column(name = "images", columnDefinition = "TEXT")
    private String images;

    @Column(name = "food_variations", columnDefinition = "TEXT")
    private String foodVariations;

    @Column(name = "slug")
    private String slug;

    @Column(name = "recommended", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean recommended = false;

    @Column(name = "organic", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean organic = false;

    @Column(name = "maximum_cart_quantity", columnDefinition = "INT DEFAULT 0")
    private Integer maximumCartQuantity = 0;

    @Column(name = "minimum_cart_quantity", columnDefinition = "INT DEFAULT 1")
    private Integer minimumCartQuantity = 1;

    @Column(name = "meta_title")
    private String metaTitle;

    @Column(name = "meta_description")
    private String metaDescription;

    @Column(name = "meta_image")
    private String metaImage;

    @Column(name = "order_count", columnDefinition = "INT DEFAULT 0")
    private Integer orderCount = 0;

    @Column(name = "avg_rating", columnDefinition = "DECIMAL(3,2) DEFAULT 0.00")
    private Double avgRating = 0.0;

    @Column(name = "rating_count", columnDefinition = "INT DEFAULT 0")
    private Integer ratingCount = 0;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Item() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getImageFullUrl() { return imageFullUrl; }
    public void setImageFullUrl(String imageFullUrl) { this.imageFullUrl = imageFullUrl; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getCategoryIds() { return categoryIds; }
    public void setCategoryIds(String categoryIds) { this.categoryIds = categoryIds; }

    public String getVariations() { return variations; }
    public void setVariations(String variations) { this.variations = variations; }

    public String getAddOns() { return addOns; }
    public void setAddOns(String addOns) { this.addOns = addOns; }

    public String getAttributes() { return attributes; }
    public void setAttributes(String attributes) { this.attributes = attributes; }

    public String getChoiceOptions() { return choiceOptions; }
    public void setChoiceOptions(String choiceOptions) { this.choiceOptions = choiceOptions; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getTax() { return tax; }
    public void setTax(Double tax) { this.tax = tax; }

    public String getTaxType() { return taxType; }
    public void setTaxType(String taxType) { this.taxType = taxType; }

    public Double getDiscount() { return discount; }
    public void setDiscount(Double discount) { this.discount = discount; }

    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }

    public LocalTime getAvailableTimeStarts() { return availableTimeStarts; }
    public void setAvailableTimeStarts(LocalTime availableTimeStarts) { this.availableTimeStarts = availableTimeStarts; }

    public LocalTime getAvailableTimeEnds() { return availableTimeEnds; }
    public void setAvailableTimeEnds(LocalTime availableTimeEnds) { this.availableTimeEnds = availableTimeEnds; }

    public Integer getVeg() { return veg; }
    public void setVeg(Integer veg) { this.veg = veg; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public Store getStore() { return store; }
    public void setStore(Store store) { this.store = store; }

    public Long getUnitId() { return unitId; }
    public void setUnitId(Long unitId) { this.unitId = unitId; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }

    public String getFoodVariations() { return foodVariations; }
    public void setFoodVariations(String foodVariations) { this.foodVariations = foodVariations; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public Boolean getRecommended() { return recommended; }
    public void setRecommended(Boolean recommended) { this.recommended = recommended; }

    public Boolean getOrganic() { return organic; }
    public void setOrganic(Boolean organic) { this.organic = organic; }

    public Integer getMaximumCartQuantity() { return maximumCartQuantity; }
    public void setMaximumCartQuantity(Integer maximumCartQuantity) { this.maximumCartQuantity = maximumCartQuantity; }

    public Integer getMinimumCartQuantity() { return minimumCartQuantity; }
    public void setMinimumCartQuantity(Integer minimumCartQuantity) { this.minimumCartQuantity = minimumCartQuantity; }

    public String getMetaTitle() { return metaTitle; }
    public void setMetaTitle(String metaTitle) { this.metaTitle = metaTitle; }

    public String getMetaDescription() { return metaDescription; }
    public void setMetaDescription(String metaDescription) { this.metaDescription = metaDescription; }

    public String getMetaImage() { return metaImage; }
    public void setMetaImage(String metaImage) { this.metaImage = metaImage; }

    public Integer getOrderCount() { return orderCount; }
    public void setOrderCount(Integer orderCount) { this.orderCount = orderCount; }

    public Double getAvgRating() { return avgRating; }
    public void setAvgRating(Double avgRating) { this.avgRating = avgRating; }

    public Integer getRatingCount() { return ratingCount; }
    public void setRatingCount(Integer ratingCount) { this.ratingCount = ratingCount; }

    public List<CartItem> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItem> cartItems) { this.cartItems = cartItems; }

    public List<OrderDetail> getOrderDetails() { return orderDetails; }
    public void setOrderDetails(List<OrderDetail> orderDetails) { this.orderDetails = orderDetails; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
