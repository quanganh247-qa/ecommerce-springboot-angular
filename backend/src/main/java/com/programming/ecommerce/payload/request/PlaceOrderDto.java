package com.programming.ecommerce.payload.request;

import lombok.Data;

@Data
public class PlaceOrderDto {
    private String userId;
    private String address;
    private String orderDescription;
}
