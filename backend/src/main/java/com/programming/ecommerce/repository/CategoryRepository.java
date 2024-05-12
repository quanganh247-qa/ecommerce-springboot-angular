package com.programming.ecommerce.repository;

import com.programming.ecommerce.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String>{
}
