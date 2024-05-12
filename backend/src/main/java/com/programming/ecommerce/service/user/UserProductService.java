package com.programming.ecommerce.service.user;

import com.programming.ecommerce.payload.request.ProductDetailDto;
import com.programming.ecommerce.payload.request.ProductRequest;

import java.util.List;

public interface UserProductService {
    List<ProductRequest> getAllProducts();

    List<ProductRequest> getAllProductByNameContaining(String name);

    ProductDetailDto getProductDetailById(String productId);
}
