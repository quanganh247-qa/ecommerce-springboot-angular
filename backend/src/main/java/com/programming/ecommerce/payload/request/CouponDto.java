package com.programming.ecommerce.payload.request;

import lombok.Data;

import java.util.Date;

@Data
public class CouponDto {
    private String id;
    private String name;
    private String code;
    private Long discount;
    private Date expirationDate;
}
