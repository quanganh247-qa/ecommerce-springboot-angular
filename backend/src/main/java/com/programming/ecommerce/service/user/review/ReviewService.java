package com.programming.ecommerce.service.user.review;

import com.programming.ecommerce.models.OrderedProductResponseDto;
import com.programming.ecommerce.payload.request.ReviewDto;

import java.io.IOException;

public interface ReviewService {
    OrderedProductResponseDto getOrderedProductDetailsById(String orderId);

    ReviewDto giveReview(ReviewDto reviewDto) throws IOException;

//    ReviewDto giveReview(ReviewDto reviewDto, String productId) throws IOException;
}
