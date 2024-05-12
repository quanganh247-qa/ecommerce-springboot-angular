package com.programming.ecommerce.payload.request;

import lombok.Data;

@Data
public class AddProductToCartDto {
    private String userId;
    private String productId;
}
