package com.ecommerce.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.demo.model.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    
}
