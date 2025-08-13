package com.sixammart.service;

import com.sixammart.dto.CartRequest;
import com.sixammart.entity.CartItem;
import com.sixammart.entity.Item;
import com.sixammart.entity.User;
import com.sixammart.repository.CartItemRepository;
import com.sixammart.repository.ItemRepository;
import com.sixammart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CartItem> getCartItems(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public CartItem addToCart(Long userId, CartRequest request) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        Item item = itemRepository.findById(request.getItemId())
            .orElseThrow(() -> new RuntimeException("Item not found with id: " + request.getItemId()));

        // Check if item already exists in cart
        Optional<CartItem> existingCartItem = cartItemRepository.findByUserIdAndItemId(userId, request.getItemId());
        
        if (existingCartItem.isPresent()) {
            // Update quantity
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            cartItem.setVariations(request.getVariations());
            cartItem.setAddOns(request.getAddOns());
            cartItem.setItemType(request.getItemType());
            return cartItemRepository.save(cartItem);
        } else {
            // Create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setItem(item);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(item.getPrice());
            cartItem.setVariations(request.getVariations());
            cartItem.setAddOns(request.getAddOns());
            cartItem.setItemType(request.getItemType());
            return cartItemRepository.save(cartItem);
        }
    }

    public CartItem updateCartItem(Long userId, Long cartItemId, CartRequest request) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
            .orElseThrow(() -> new RuntimeException("Cart item not found with id: " + cartItemId));
        
        if (!cartItem.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized access to cart item");
        }

        cartItem.setQuantity(request.getQuantity());
        cartItem.setVariations(request.getVariations());
        cartItem.setAddOns(request.getAddOns());
        cartItem.setItemType(request.getItemType());
        
        return cartItemRepository.save(cartItem);
    }

    public void removeFromCart(Long userId, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
            .orElseThrow(() -> new RuntimeException("Cart item not found with id: " + cartItemId));
        
        if (!cartItem.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized access to cart item");
        }

        cartItemRepository.delete(cartItem);
    }

    public void clearCart(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }

    public void clearCartByStore(Long userId, Long storeId) {
        cartItemRepository.deleteByUserIdAndStoreId(userId, storeId);
    }

    public Long getCartItemsCount(Long userId) {
        return cartItemRepository.countByUserId(userId);
    }

    public Double getCartTotal(Long userId) {
        Double total = cartItemRepository.getTotalAmountByUserId(userId);
        return total != null ? total : 0.0;
    }

    public List<Long> getCartStoreIds(Long userId) {
        return cartItemRepository.findDistinctStoreIdsByUserId(userId);
    }

    public List<CartItem> getCartItemsByStore(Long userId, Long storeId) {
        return cartItemRepository.findByUserIdAndStoreId(userId, storeId);
    }
}
