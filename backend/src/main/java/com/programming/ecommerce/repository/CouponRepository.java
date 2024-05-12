package com.programming.ecommerce.repository;

import com.programming.ecommerce.models.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends MongoRepository<Coupon,String> {
    boolean existsByCode(String code);
    Optional<Coupon> findByCode(String code);
}
