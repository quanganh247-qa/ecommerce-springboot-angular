package com.programming.ecommerce.payload.request;

import lombok.Data;

@Data
public class CartItemsDto {
    private String id;
    private Long price;
    private Long quantity;
    private String productId;
    private String orderId;
    private String productName;
    private byte[] returnedImg;
    private String userId;
}
