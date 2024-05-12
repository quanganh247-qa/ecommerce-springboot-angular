package com.programming.ecommerce.controller;

import com.programming.ecommerce.payload.request.OrderDto;
import com.programming.ecommerce.service.user.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TrackingController {

    private  final CartService cartService;

    @GetMapping("/order/{trackID}")
    public ResponseEntity<OrderDto> searchOrder(@PathVariable("trackID") UUID trackID){
        OrderDto order = cartService.searchOrder(trackID);
        if(order!=null){
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

}
