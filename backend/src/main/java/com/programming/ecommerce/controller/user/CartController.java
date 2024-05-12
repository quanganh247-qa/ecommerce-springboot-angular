package com.programming.ecommerce.controller.user;

import com.programming.ecommerce.payload.request.AddProductToCartDto;
import com.programming.ecommerce.payload.request.OrderDto;
import com.programming.ecommerce.payload.request.PlaceOrderDto;
import com.programming.ecommerce.service.user.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;
    @PostMapping("/cart")
    public ResponseEntity<?> addToCart(@RequestBody AddProductToCartDto addProductToCartDto){
        return cartService.addCart(addProductToCartDto);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCart(@PathVariable("userId") String userId){
        OrderDto orderDto = cartService.getCardByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @PostMapping("/coupon/{userId}/{code}")
    public ResponseEntity<?> applyCoupon(@PathVariable("userId") String userId, @PathVariable("code") String code){
        try {
            OrderDto orderDto = cartService.applyCoupon(userId,code);
            return ResponseEntity.ok(orderDto);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/increaseQuantity")
    public ResponseEntity<?> increaseProductQuantity(@RequestBody AddProductToCartDto addProductToCartDto){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.increaseProductQuantity(addProductToCartDto));
    }
    @PostMapping("/decreaseQuantity")
    public ResponseEntity<?> decreaseProductQuantity(@RequestBody AddProductToCartDto addProductToCartDto){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.decreaseProductQuantity(addProductToCartDto));
    }

    @PostMapping("/placeOrder")
    public ResponseEntity placeOrder(@RequestBody PlaceOrderDto placeOrderDto){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.placeOrder(placeOrderDto));
    }
    @GetMapping("/myOrders/{userId}")
    public ResponseEntity<List<OrderDto>> getMyPlacedOrders(@PathVariable("userId") String userId){
        return ResponseEntity.ok(cartService.getMyPLacedOrders(userId));
    }
}
