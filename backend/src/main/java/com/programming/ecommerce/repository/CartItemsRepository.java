package com.programming.ecommerce.repository;

import com.programming.ecommerce.models.CartItems;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemsRepository extends MongoRepository<CartItems, String>{

    Optional<CartItems> findByProductIdAndOrderIdAndUserId(String productId, String orderId, String userId);
}
