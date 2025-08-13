package com.sixammart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "stores")
@EntityListeners(AuditingEntityListener.class)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "logo")
    private String logo;

    @Column(name = "logo_full_url")
    private String logoFullUrl;

    @NotNull
    @Column(name = "latitude")
    private Double latitude;

    @NotNull
    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "address")
    private String address;

    @Column(name = "footer_text")
    private String footerText;

    @Column(name = "minimum_order", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double minimumOrder = 0.0;

    @Column(name = "commission", columnDefinition = "DECIMAL(5,2)")
    private Double commission;

    @Column(name = "schedule_order", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean scheduleOrder = false;

    @Column(name = "status", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status = true;

    @NotNull
    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "free_delivery", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean freeDelivery = false;

    @Column(name = "cover_photo")
    private String coverPhoto;

    @Column(name = "cover_photo_full_url")
    private String coverPhotoFullUrl;

    @Column(name = "delivery", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean delivery = true;

    @Column(name = "take_away", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean takeAway = true;

    @Column(name = "item_section", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean itemSection = true;

    @Column(name = "tax", columnDefinition = "DECIMAL(5,2) DEFAULT 0.00")
    private Double tax = 0.0;

    @NotNull
    @Column(name = "zone_id")
    private Long zoneId;

    @Column(name = "reviews_section", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean reviewsSection = true;

    @Column(name = "active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean active = true;

    @Column(name = "off_day")
    private String offDay;

    @Column(name = "self_delivery_system", columnDefinition = "INT DEFAULT 0")
    private Integer selfDeliverySystem = 0;

    @Column(name = "pos_system", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean posSystem = false;

    @Column(name = "minimum_shipping_charge", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double minimumShippingCharge = 0.0;

    @Column(name = "delivery_time")
    private String deliveryTime;

    @Column(name = "veg", columnDefinition = "INT DEFAULT 1")
    private Integer veg = 1;

    @Column(name = "non_veg", columnDefinition = "INT DEFAULT 1")
    private Integer nonVeg = 1;

    @Column(name = "order_count", columnDefinition = "INT DEFAULT 0")
    private Integer orderCount = 0;

    @Column(name = "total_order", columnDefinition = "INT DEFAULT 0")
    private Integer totalOrder = 0;

    @NotNull
    @Column(name = "module_id")
    private Long moduleId;

    @Column(name = "order_place_to_schedule_interval", columnDefinition = "INT DEFAULT 0")
    private Integer orderPlaceToScheduleInterval = 0;

    @Column(name = "featured", columnDefinition = "INT DEFAULT 0")
    private Integer featured = 0;

    @Column(name = "per_km_shipping_charge", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double perKmShippingCharge = 0.0;

    @Column(name = "prescription_order", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean prescriptionOrder = false;

    @Column(name = "slug")
    private String slug;

    @Column(name = "maximum_shipping_charge", columnDefinition = "DECIMAL(10,2)")
    private Double maximumShippingCharge;

    @Column(name = "cutlery", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean cutlery = false;

    @Column(name = "meta_title")
    private String metaTitle;

    @Column(name = "meta_description")
    private String metaDescription;

    @Column(name = "meta_image")
    private String metaImage;

    @Column(name = "announcement_active", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean announcementActive = false;

    @Column(name = "announcement_message")
    private String announcementMessage;

    @Column(name = "avg_rating", columnDefinition = "DECIMAL(3,2) DEFAULT 0.00")
    private Double avgRating = 0.0;

    @Column(name = "rating_count", columnDefinition = "INT DEFAULT 0")
    private Integer ratingCount = 0;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Store() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public String getLogoFullUrl() { return logoFullUrl; }
    public void setLogoFullUrl(String logoFullUrl) { this.logoFullUrl = logoFullUrl; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getFooterText() { return footerText; }
    public void setFooterText(String footerText) { this.footerText = footerText; }

    public Double getMinimumOrder() { return minimumOrder; }
    public void setMinimumOrder(Double minimumOrder) { this.minimumOrder = minimumOrder; }

    public Double getCommission() { return commission; }
    public void setCommission(Double commission) { this.commission = commission; }

    public Boolean getScheduleOrder() { return scheduleOrder; }
    public void setScheduleOrder(Boolean scheduleOrder) { this.scheduleOrder = scheduleOrder; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }

    public Boolean getFreeDelivery() { return freeDelivery; }
    public void setFreeDelivery(Boolean freeDelivery) { this.freeDelivery = freeDelivery; }

    public String getCoverPhoto() { return coverPhoto; }
    public void setCoverPhoto(String coverPhoto) { this.coverPhoto = coverPhoto; }

    public String getCoverPhotoFullUrl() { return coverPhotoFullUrl; }
    public void setCoverPhotoFullUrl(String coverPhotoFullUrl) { this.coverPhotoFullUrl = coverPhotoFullUrl; }

    public Boolean getDelivery() { return delivery; }
    public void setDelivery(Boolean delivery) { this.delivery = delivery; }

    public Boolean getTakeAway() { return takeAway; }
    public void setTakeAway(Boolean takeAway) { this.takeAway = takeAway; }

    public Boolean getItemSection() { return itemSection; }
    public void setItemSection(Boolean itemSection) { this.itemSection = itemSection; }

    public Double getTax() { return tax; }
    public void setTax(Double tax) { this.tax = tax; }

    public Long getZoneId() { return zoneId; }
    public void setZoneId(Long zoneId) { this.zoneId = zoneId; }

    public Boolean getReviewsSection() { return reviewsSection; }
    public void setReviewsSection(Boolean reviewsSection) { this.reviewsSection = reviewsSection; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public String getOffDay() { return offDay; }
    public void setOffDay(String offDay) { this.offDay = offDay; }

    public Integer getSelfDeliverySystem() { return selfDeliverySystem; }
    public void setSelfDeliverySystem(Integer selfDeliverySystem) { this.selfDeliverySystem = selfDeliverySystem; }

    public Boolean getPosSystem() { return posSystem; }
    public void setPosSystem(Boolean posSystem) { this.posSystem = posSystem; }

    public Double getMinimumShippingCharge() { return minimumShippingCharge; }
    public void setMinimumShippingCharge(Double minimumShippingCharge) { this.minimumShippingCharge = minimumShippingCharge; }

    public String getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(String deliveryTime) { this.deliveryTime = deliveryTime; }

    public Integer getVeg() { return veg; }
    public void setVeg(Integer veg) { this.veg = veg; }

    public Integer getNonVeg() { return nonVeg; }
    public void setNonVeg(Integer nonVeg) { this.nonVeg = nonVeg; }

    public Integer getOrderCount() { return orderCount; }
    public void setOrderCount(Integer orderCount) { this.orderCount = orderCount; }

    public Integer getTotalOrder() { return totalOrder; }
    public void setTotalOrder(Integer totalOrder) { this.totalOrder = totalOrder; }

    public Long getModuleId() { return moduleId; }
    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }

    public Integer getOrderPlaceToScheduleInterval() { return orderPlaceToScheduleInterval; }
    public void setOrderPlaceToScheduleInterval(Integer orderPlaceToScheduleInterval) { this.orderPlaceToScheduleInterval = orderPlaceToScheduleInterval; }

    public Integer getFeatured() { return featured; }
    public void setFeatured(Integer featured) { this.featured = featured; }

    public Double getPerKmShippingCharge() { return perKmShippingCharge; }
    public void setPerKmShippingCharge(Double perKmShippingCharge) { this.perKmShippingCharge = perKmShippingCharge; }

    public Boolean getPrescriptionOrder() { return prescriptionOrder; }
    public void setPrescriptionOrder(Boolean prescriptionOrder) { this.prescriptionOrder = prescriptionOrder; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public Double getMaximumShippingCharge() { return maximumShippingCharge; }
    public void setMaximumShippingCharge(Double maximumShippingCharge) { this.maximumShippingCharge = maximumShippingCharge; }

    public Boolean getCutlery() { return cutlery; }
    public void setCutlery(Boolean cutlery) { this.cutlery = cutlery; }

    public String getMetaTitle() { return metaTitle; }
    public void setMetaTitle(String metaTitle) { this.metaTitle = metaTitle; }

    public String getMetaDescription() { return metaDescription; }
    public void setMetaDescription(String metaDescription) { this.metaDescription = metaDescription; }

    public String getMetaImage() { return metaImage; }
    public void setMetaImage(String metaImage) { this.metaImage = metaImage; }

    public Boolean getAnnouncementActive() { return announcementActive; }
    public void setAnnouncementActive(Boolean announcementActive) { this.announcementActive = announcementActive; }

    public String getAnnouncementMessage() { return announcementMessage; }
    public void setAnnouncementMessage(String announcementMessage) { this.announcementMessage = announcementMessage; }

    public Double getAvgRating() { return avgRating; }
    public void setAvgRating(Double avgRating) { this.avgRating = avgRating; }

    public Integer getRatingCount() { return ratingCount; }
    public void setRatingCount(Integer ratingCount) { this.ratingCount = ratingCount; }

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }

    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
