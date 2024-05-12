package com.programming.ecommerce.repository;

import com.programming.ecommerce.models.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String>{


    List<Wishlist> findAllByUserId(String userId);

}
