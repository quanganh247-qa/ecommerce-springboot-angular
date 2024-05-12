package com.programming.ecommerce.service.user.wishlist;

import com.programming.ecommerce.models.Product;
import com.programming.ecommerce.models.User;
import com.programming.ecommerce.models.Wishlist;
import com.programming.ecommerce.payload.request.WishlistDto;
import com.programming.ecommerce.repository.ProductRepository;
import com.programming.ecommerce.repository.UserRepository;
import com.programming.ecommerce.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WishlistServiceImpl implements WishlistService{
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public WishlistDto addProductToWishlist(WishlistDto wishlistDto) {
        Optional<User> userOptional = userRepository.findById(wishlistDto.getUserId());
        Optional<Product> productOptional = productRepository.findById(wishlistDto.getProductId());
        if(userOptional.isPresent() && productOptional.isPresent()){
            Wishlist wishlist = new Wishlist();
            wishlist.setUser(userOptional.get());
            wishlist.setProduct(productOptional.get());
            return wishlistRepository.save(wishlist).getWishlistDto();
        }
        return null;
    }

    @Override
    public List<WishlistDto> getWishlistByUserId(String userId) {
        return wishlistRepository.findAllByUserId(userId).stream().map(Wishlist::getWishlistDto).toList();
    }
}
