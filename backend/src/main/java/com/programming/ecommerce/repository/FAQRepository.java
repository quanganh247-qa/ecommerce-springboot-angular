package com.programming.ecommerce.repository;

import com.programming.ecommerce.models.FAQ;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQRepository extends MongoRepository<FAQ,String> {
}
