package com.programming.ecommerce.service.admin.admincoupon;

import com.programming.ecommerce.models.Coupon;
import com.programming.ecommerce.repository.CouponRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AdminCouponServiceImpl implements AdminCouponService {

    private final CouponRepository couponRepository;

    @Override
    public Coupon createCoupon(Coupon coupon){
        if(couponRepository.existsByCode(coupon.getCode())){
            throw new ValidationException("Coupon already exists");
        }
        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> getAllCoupons(){
        return  couponRepository.findAll();
    }
}
