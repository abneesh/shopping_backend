package com.sixammart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "banners")
@EntityListeners(AuditingEntityListener.class)
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @Column(name = "image")
    private String image;

    @Column(name = "image_full_url")
    private String imageFullUrl;

    @Column(name = "status", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status = true;

    @Column(name = "data")
    private String data;

    @Column(name = "module_id")
    private Long moduleId;

    @Column(name = "featured", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean featured = false;

    @Column(name = "zone_id")
    private Long zoneId;

    @Column(name = "default_link")
    private String defaultLink;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Banner() {}

    public Banner(String title, String type, String image, Long moduleId) {
        this.title = title;
        this.type = type;
        this.image = image;
        this.moduleId = moduleId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getImageFullUrl() { return imageFullUrl; }
    public void setImageFullUrl(String imageFullUrl) { this.imageFullUrl = imageFullUrl; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public Long getModuleId() { return moduleId; }
    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }

    public Boolean getFeatured() { return featured; }
    public void setFeatured(Boolean featured) { this.featured = featured; }

    public Long getZoneId() { return zoneId; }
    public void setZoneId(Long zoneId) { this.zoneId = zoneId; }

    public String getDefaultLink() { return defaultLink; }
    public void setDefaultLink(String defaultLink) { this.defaultLink = defaultLink; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
