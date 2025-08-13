package com.sixammart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "modules")
@EntityListeners(AuditingEntityListener.class)
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "module_name")
    private String moduleName;

    @Column(name = "module_type")
    private String moduleType;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "thumbnail_full_url")
    private String thumbnailFullUrl;

    @Column(name = "status", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status = true;

    @Column(name = "stores_count", columnDefinition = "INT DEFAULT 0")
    private Integer storesCount = 0;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "all_zone_service", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean allZoneService = false;

    @Column(name = "icon")
    private String icon;

    @Column(name = "icon_full_url")
    private String iconFullUrl;

    @Column(name = "theme_id")
    private Long themeId;

    @Column(name = "slug")
    private String slug;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Module() {}

    public Module(String moduleName, String moduleType, String thumbnail) {
        this.moduleName = moduleName;
        this.moduleType = moduleType;
        this.thumbnail = thumbnail;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getModuleName() { return moduleName; }
    public void setModuleName(String moduleName) { this.moduleName = moduleName; }

    public String getModuleType() { return moduleType; }
    public void setModuleType(String moduleType) { this.moduleType = moduleType; }

    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }

    public String getThumbnailFullUrl() { return thumbnailFullUrl; }
    public void setThumbnailFullUrl(String thumbnailFullUrl) { this.thumbnailFullUrl = thumbnailFullUrl; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public Integer getStoresCount() { return storesCount; }
    public void setStoresCount(Integer storesCount) { this.storesCount = storesCount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getAllZoneService() { return allZoneService; }
    public void setAllZoneService(Boolean allZoneService) { this.allZoneService = allZoneService; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getIconFullUrl() { return iconFullUrl; }
    public void setIconFullUrl(String iconFullUrl) { this.iconFullUrl = iconFullUrl; }

    public Long getThemeId() { return themeId; }
    public void setThemeId(Long themeId) { this.themeId = themeId; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
