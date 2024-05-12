package com.programming.ecommerce.service.user.cart;

import com.programming.ecommerce.payload.request.AddProductToCartDto;
import com.programming.ecommerce.payload.request.OrderDto;
import com.programming.ecommerce.payload.request.PlaceOrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CartService {
    ResponseEntity<?> addCart(AddProductToCartDto addProductToCartDto);

    OrderDto getCardByUserId(String userId);

    OrderDto applyCoupon(String userId, String code);

    OrderDto increaseProductQuantity(AddProductToCartDto addProductToCartDto);

    OrderDto decreaseProductQuantity(AddProductToCartDto addProductToCartDto);

    OrderDto placeOrder(PlaceOrderDto placeOrderDto);

    List<OrderDto> getMyPLacedOrders(String userId);

    OrderDto searchOrder(UUID trackId);
}
