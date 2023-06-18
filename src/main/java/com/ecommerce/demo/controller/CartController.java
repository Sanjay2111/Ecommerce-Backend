package com.ecommerce.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.service.CartService;
import com.ecommerce.demo.service.ProductService;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

   @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
    // Fetch the product from the productService using the productId
    Product product = productService.getProductById(cart.getProductId());
    if (product == null) {
        return ResponseEntity.badRequest().build(); // Return bad request if product doesn't exist
    }

    // Set the price of the product in the cart
    cart.setPrice(product.getPrice());
    
    // Set the name and URL of the product in the cart
    cart.setProductName(product.getName());
    cart.setUrl(product.getUrl());

    // Save the cart using the cartService
    Cart createdCart = cartService.createCart(cart);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
}

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable String id, @RequestBody Cart cart) {
        Optional<Cart> existingCart = cartService.getCartById(id);
        if (existingCart.isPresent()) {
            cart.setId(id);
            Cart updatedCart = cartService.updateCart(cart);
            return ResponseEntity.ok(updatedCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable String id) {
        Optional<Cart> existingCart = cartService.getCartById(id);
        if (existingCart.isPresent()) {
            cartService.deleteCart(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
