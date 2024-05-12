package com.programming.ecommerce.controller.user;

import com.programming.ecommerce.payload.request.OrderDto;
import com.programming.ecommerce.payload.request.ProductRequest;
import com.programming.ecommerce.service.user.UserProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserProductService userProductService;
    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductRequest> getAllProducts(){
        return userProductService.getAllProducts();
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductRequest>> getAllProductByNameContaining(@PathVariable("name") String name){
        List<ProductRequest> productRequests = userProductService.getAllProductByNameContaining(name);
        return ResponseEntity.ok(productRequests);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductDetailById(@PathVariable("productId") String productId){
        return ResponseEntity.ok(userProductService.getProductDetailById(productId));
    }

}
