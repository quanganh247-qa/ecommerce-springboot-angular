package com.programming.ecommerce.service.admin.adminproduct;


import com.programming.ecommerce.models.Product;
import com.programming.ecommerce.payload.request.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {
//    ProductRequest addProduct(ProductRequest productRequest) throws IOException;


    ProductRequest addProduct(ProductRequest productRequest, MultipartFile image) throws IOException;

    List<ProductRequest> getAllProducts();

    List<ProductRequest> getAllProductByNameContaining(String name);

    void deleteProduct(String id);

    ProductRequest getProductById(String productId);

    ProductRequest updateProduct(String productId, ProductRequest productRequest);
}
