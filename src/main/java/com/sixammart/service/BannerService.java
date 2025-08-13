package com.sixammart.service;

import com.sixammart.entity.Banner;
import com.sixammart.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    public List<Banner> getAllActiveBanners() {
        return bannerRepository.findAllActiveBanners();
    }

    public List<Banner> getBannersByModule(Long moduleId) {
        return bannerRepository.findByModuleIdAndActive(moduleId);
    }

    public List<Banner> getBannersByZone(Long zoneId) {
        return bannerRepository.findByZoneIdAndActive(zoneId);
    }

    public List<Banner> getBannersByModuleAndZone(Long moduleId, Long zoneId) {
        return bannerRepository.findByModuleIdAndZoneIdAndActive(moduleId, zoneId);
    }

    public List<Banner> getFeaturedBanners() {
        return bannerRepository.findFeaturedBanners();
    }

    public List<Banner> getBannersByType(String type) {
        return bannerRepository.findByTypeAndActive(type);
    }

    public Banner getBannerById(Long id) {
        return bannerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Banner not found with id: " + id));
    }

    public Banner createBanner(Banner banner) {
        // Set default values
        if (banner.getImageFullUrl() == null && banner.getImage() != null) {
            banner.setImageFullUrl("/uploads/banners/" + banner.getImage());
        }
        
        return bannerRepository.save(banner);
    }

    public Banner updateBanner(Long id, Banner bannerDetails) {
        Banner banner = getBannerById(id);
        
        banner.setTitle(bannerDetails.getTitle());
        banner.setType(bannerDetails.getType());
        banner.setImage(bannerDetails.getImage());
        banner.setData(bannerDetails.getData());
        banner.setStatus(bannerDetails.getStatus());
        banner.setFeatured(bannerDetails.getFeatured());
        
        if (banner.getImageFullUrl() == null && banner.getImage() != null) {
            banner.setImageFullUrl("/uploads/banners/" + banner.getImage());
        }
        
        return bannerRepository.save(banner);
    }

    public void deleteBanner(Long id) {
        Banner banner = getBannerById(id);
        banner.setStatus(false);
        bannerRepository.save(banner);
    }

    public Long getActiveBannersCount() {
        return bannerRepository.countActiveBanners();
    }
}
