package com.programming.ecommerce.service.user.review;

import com.programming.ecommerce.models.*;
import com.programming.ecommerce.payload.request.ProductRequest;
import com.programming.ecommerce.payload.request.ReviewDto;
import com.programming.ecommerce.repository.OrderRepository;
import com.programming.ecommerce.repository.ProductRepository;
import com.programming.ecommerce.repository.ReviewRepository;
import com.programming.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    @Override
    public OrderedProductResponseDto getOrderedProductDetailsById(String orderId){
        Optional< Order> optionalOrder = orderRepository.findById(orderId);
        OrderedProductResponseDto orderedProductResponseDto = new OrderedProductResponseDto();
        if(optionalOrder.isPresent()){
            orderedProductResponseDto.setOrderAmount(optionalOrder.get().getAmount());
            List<ProductRequest> productRequests = new ArrayList<>();
            for(CartItems cartItems: optionalOrder.get().getCartItems()){
                ProductRequest productRequest = new ProductRequest();
                productRequest.setName(cartItems.getProduct().getName());
                productRequest.setDescription(cartItems.getProduct().getDescription());
                productRequest.setPrice(cartItems.getProduct().getPrice());
                productRequest.setQuantity(cartItems.getQuantity());
                productRequest.setByteImg(cartItems.getProduct().getImage());
                productRequests.add(productRequest);

            }
            orderedProductResponseDto.setProductRequestList(productRequests);
        }
        return orderedProductResponseDto;
    }

    @Override
    public ReviewDto giveReview(ReviewDto reviewDto) throws IOException {
        Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
        Optional<Product> optionalProduct = productRepository.findById(reviewDto.getProductId());
        if(optionalUser.isPresent() && optionalProduct.isPresent()){
            Review review = new Review();
            review.setRating(reviewDto.getRating());
            review.setDescription(reviewDto.getDescription());
            review.setUser(optionalUser.get());
            review.setProduct(optionalProduct.get());
            review.setImg(reviewDto.getImg().getBytes());
            return reviewRepository.save(review).getDto();
        }



        return null;
    }

}
