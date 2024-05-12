package com.programming.ecommerce.controller.user;

import com.programming.ecommerce.payload.request.WishlistDto;
import com.programming.ecommerce.service.user.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WishlistController {
    private final WishlistService wishlistService;

    @PostMapping("/wishlist")
    public ResponseEntity<?> addToWishlist(@RequestBody WishlistDto wishlistDto){
        WishlistDto wishlist = wishlistService.addProductToWishlist(wishlistDto);
        if(wishlist!=null){
            return ResponseEntity.status(HttpStatus.OK).body(wishlist);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product or User not found");
    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WishlistDto>> getWishlistByUserid(@PathVariable("userId") String userId){
        return ResponseEntity.status(HttpStatus.OK).body(wishlistService.getWishlistByUserId(userId));
    }

}
