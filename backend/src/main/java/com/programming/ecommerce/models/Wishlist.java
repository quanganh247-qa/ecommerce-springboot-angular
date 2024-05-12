package com.programming.ecommerce.models;

import com.programming.ecommerce.payload.request.WishlistDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "wishlist")
public class Wishlist {
    @Id
    private String id;
    @DBRef
    private Product product;
    @DBRef
    private User user;

    public WishlistDto getWishlistDto(){
        WishlistDto wishlistDto = new WishlistDto();
        wishlistDto.setId(id);
        wishlistDto.setPrice(product.getPrice());
        wishlistDto.setProductId(product.getId());
        wishlistDto.setUserId(user.getId());
        wishlistDto.setReturnedImg(product.getImage());
        wishlistDto.setProductDescription(product.getDescription());
        wishlistDto.setProductName(product.getName());
        return wishlistDto;
    }
}
