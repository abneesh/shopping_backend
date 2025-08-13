package com.sixammart.service;

import com.sixammart.entity.Item;
import com.sixammart.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> getAllActiveItems() {
        return itemRepository.findAllActiveItems();
    }

    public Page<Item> getAllActiveItems(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return itemRepository.findAllActiveItems(pageable);
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }

    public Item getItemBySlug(String slug) {
        return itemRepository.findBySlug(slug)
            .orElseThrow(() -> new RuntimeException("Item not found with slug: " + slug));
    }

    public List<Item> getItemsByStore(Long storeId) {
        return itemRepository.findByStoreIdAndActive(storeId);
    }

    public List<Item> getItemsByCategory(Long categoryId) {
        return itemRepository.findByCategoryIdAndActive(categoryId);
    }

    public List<Item> getItemsByStoreAndCategory(Long storeId, Long categoryId) {
        return itemRepository.findByStoreIdAndCategoryIdAndActive(storeId, categoryId);
    }

    public List<Item> getRecommendedItems() {
        return itemRepository.findRecommendedItems();
    }

    public List<Item> getOrganicItems() {
        return itemRepository.findOrganicItems();
    }

    public List<Item> getVegItems() {
        return itemRepository.findByVegAndActive(1);
    }

    public List<Item> getNonVegItems() {
        return itemRepository.findByVegAndActive(0);
    }

    public List<Item> getDiscountedItems() {
        return itemRepository.findDiscountedItems();
    }

    public List<Item> getPopularItems(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return itemRepository.findPopularItems(pageable);
    }

    public List<Item> getPopularItemsByStore(Long storeId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return itemRepository.findPopularItemsByStore(storeId, pageable);
    }

    public List<Item> getTopRatedItems(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return itemRepository.findTopRatedItems(pageable);
    }

    public List<Item> searchItemsByName(String name) {
        return itemRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Item> getItemsByZone(Long zoneId) {
        return itemRepository.findByZoneIdAndActive(zoneId);
    }

    public Long getActiveItemsCount() {
        return itemRepository.countActiveItems();
    }

    public Item createItem(Item item) {
        if (item.getImageFullUrl() == null && item.getImage() != null) {
            item.setImageFullUrl("/uploads/items/" + item.getImage());
        }
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item itemDetails) {
        Item item = getItemById(id);
        
        item.setName(itemDetails.getName());
        item.setDescription(itemDetails.getDescription());
        item.setImage(itemDetails.getImage());
        item.setCategoryId(itemDetails.getCategoryId());
        item.setPrice(itemDetails.getPrice());
        item.setTax(itemDetails.getTax());
        item.setDiscount(itemDetails.getDiscount());
        item.setStatus(itemDetails.getStatus());
        item.setVeg(itemDetails.getVeg());
        item.setRecommended(itemDetails.getRecommended());
        item.setOrganic(itemDetails.getOrganic());
        
        if (item.getImageFullUrl() == null && item.getImage() != null) {
            item.setImageFullUrl("/uploads/items/" + item.getImage());
        }
        
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        Item item = getItemById(id);
        item.setStatus(false);
        itemRepository.save(item);
    }

    public Item updateItemStatus(Long id, Boolean status) {
        Item item = getItemById(id);
        item.setStatus(status);
        return itemRepository.save(item);
    }
}
