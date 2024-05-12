package com.programming.ecommerce.payload.request;

import lombok.Data;

@Data
public class WishlistDto {
    private String id;
    private String userId;
    private String productId;
    private String productName;
    private String productImg;
    private Long price;
    private String productDescription;
    private byte[] returnedImg;

}
