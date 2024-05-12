package com.programming.ecommerce.repository;

import com.programming.ecommerce.models.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    List<Product> findAllByNameContaining(String title);
//    List<Product> findAllByName(String title);
}

