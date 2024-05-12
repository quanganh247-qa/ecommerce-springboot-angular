package com.programming.ecommerce.service.admin.admincoupon;

import com.programming.ecommerce.models.Coupon;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminCouponService {
    Coupon createCoupon(Coupon coupon);

    List<Coupon> getAllCoupons();
}
