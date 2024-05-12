package com.programming.ecommerce.controller.user;

import com.programming.ecommerce.models.OrderedProductResponseDto;
import com.programming.ecommerce.payload.request.ReviewDto;
import com.programming.ecommerce.service.user.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductResponseDto> getOrderedProductDetailsById(@PathVariable("orderId") String orderId){
        return ResponseEntity.ok(reviewService.getOrderedProductDetailsById(orderId));
    }

    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@ModelAttribute("reviewDto") ReviewDto reviewDto ) throws IOException {
        ReviewDto reviewDto1 = reviewService.giveReview(reviewDto);
        if(reviewDto1 != null){
            return ResponseEntity.ok(reviewDto1);
        }

        return ResponseEntity.badRequest().body("Review not saved");
    }
}
