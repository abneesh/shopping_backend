package com.sixammart.service;

import com.sixammart.entity.Store;
import com.sixammart.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public List<Store> getAllActiveStores() {
        return storeRepository.findAllActiveStores();
    }

    public Page<Store> getAllActiveStores(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return storeRepository.findAllActiveStores(pageable);
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Store not found with id: " + id));
    }

    public Store getStoreBySlug(String slug) {
        return storeRepository.findBySlug(slug)
            .orElseThrow(() -> new RuntimeException("Store not found with slug: " + slug));
    }

    public List<Store> getStoresByZone(Long zoneId) {
        return storeRepository.findByZoneIdAndActive(zoneId);
    }

    public List<Store> getStoresByModule(Long moduleId) {
        return storeRepository.findByModuleIdAndActive(moduleId);
    }

    public List<Store> getStoresByZoneAndModule(Long zoneId, Long moduleId) {
        return storeRepository.findByZoneIdAndModuleIdAndActive(zoneId, moduleId);
    }

    public List<Store> getFeaturedStores() {
        return storeRepository.findFeaturedStores();
    }

    public List<Store> searchStoresByName(String name) {
        return storeRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Store> getStoresByVendor(Long vendorId) {
        return storeRepository.findByVendorId(vendorId);
    }

    public List<Store> getStoresWithinRadius(Double latitude, Double longitude, Double radius) {
        return storeRepository.findStoresWithinRadius(latitude, longitude, radius);
    }

    public Long getActiveStoresCount() {
        return storeRepository.countActiveStores();
    }

    public Store createStore(Store store) {
        // Set default values
        if (store.getLogoFullUrl() == null && store.getLogo() != null) {
            store.setLogoFullUrl("/uploads/stores/" + store.getLogo());
        }
        if (store.getCoverPhotoFullUrl() == null && store.getCoverPhoto() != null) {
            store.setCoverPhotoFullUrl("/uploads/stores/" + store.getCoverPhoto());
        }
        
        return storeRepository.save(store);
    }

    public Store updateStore(Long id, Store storeDetails) {
        Store store = getStoreById(id);
        
        store.setName(storeDetails.getName());
        store.setPhone(storeDetails.getPhone());
        store.setEmail(storeDetails.getEmail());
        store.setAddress(storeDetails.getAddress());
        store.setLatitude(storeDetails.getLatitude());
        store.setLongitude(storeDetails.getLongitude());
        store.setMinimumOrder(storeDetails.getMinimumOrder());
        store.setDeliveryTime(storeDetails.getDeliveryTime());
        store.setStatus(storeDetails.getStatus());
        
        return storeRepository.save(store);
    }

    public void deleteStore(Long id) {
        Store store = getStoreById(id);
        store.setStatus(false);
        store.setActive(false);
        storeRepository.save(store);
    }

    public Store updateStoreStatus(Long id, Boolean status) {
        Store store = getStoreById(id);
        store.setStatus(status);
        return storeRepository.save(store);
    }

    public Store updateStoreActiveStatus(Long id, Boolean active) {
        Store store = getStoreById(id);
        store.setActive(active);
        return storeRepository.save(store);
    }
}
