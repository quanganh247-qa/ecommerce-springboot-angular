package com.programming.ecommerce.payload.request;

import com.programming.ecommerce.models.enums.OrderStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private String id;
    private String orderDescription;
    private Date orderDate;
    private Long amount;
    private String address;
    private String payment;
    private OrderStatus orderStatus;
    private Long totalAmount;
    private Long discount;
    private UUID trackID;
    private String userName;
    private List<CartItemsDto> cartItems ;
    private String couponName;
}