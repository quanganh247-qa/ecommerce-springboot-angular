package com.programming.ecommerce.service.user.wishlist;

import com.programming.ecommerce.payload.request.WishlistDto;

import java.util.List;

public interface WishlistService {
    WishlistDto addProductToWishlist(WishlistDto wishlistDto);

    List<WishlistDto> getWishlistByUserId(String userId);
}
