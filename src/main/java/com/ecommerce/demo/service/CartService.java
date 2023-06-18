package com.ecommerce.demo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.repository.CartRepository;

@Service
public class CartService {
    
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
    
    public Optional<Cart> getCartById(String id) {
        return cartRepository.findById(id);
    }
    
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }
    
    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }
    
    public void deleteCart(String id) {
        cartRepository.deleteById(id);
    }
}
