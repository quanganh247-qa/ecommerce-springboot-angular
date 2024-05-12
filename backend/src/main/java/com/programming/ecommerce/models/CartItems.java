package com.programming.ecommerce.models;

import com.programming.ecommerce.payload.request.CartItemsDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class CartItems {

    @Id
    private String id;
    private Long price;
    private Long quantity;

    @DBRef
    private Product product;

    @DBRef
    private User user;

    @DBRef
    private Order order;

    public CartItemsDto getCartDto(){
        CartItemsDto cartItemsDto = new CartItemsDto();
        cartItemsDto.setId(id);
        cartItemsDto.setPrice(price);
        cartItemsDto.setProductId(product.getId());
        cartItemsDto.setProductName(product.getName());
        cartItemsDto.setUserId(user.getId());
        cartItemsDto.setQuantity(quantity);
        cartItemsDto.setReturnedImg(product.getImage());
        return cartItemsDto;
    }
}
