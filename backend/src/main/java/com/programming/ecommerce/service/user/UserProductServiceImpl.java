package com.programming.ecommerce.service.user;

import com.programming.ecommerce.models.Product;
import com.programming.ecommerce.payload.request.ProductDetailDto;
import com.programming.ecommerce.payload.request.ProductRequest;
import com.programming.ecommerce.repository.OrderRepository;
import com.programming.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserProductServiceImpl implements UserProductService{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    @Override
    public List<ProductRequest> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(Product::getProductRequest).collect(Collectors.toList());
    }

    @Override
    public List<ProductRequest> getAllProductByNameContaining(String name){
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getProductRequest).collect(Collectors.toList());
    }

    @Override
    public ProductDetailDto getProductDetailById(String productId){
        Product product = productRepository.findById(productId).orElse(null);
        if(product == null){
            return null;
        }
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setProductRequest(product.getProductRequest());
        return productDetailDto;
    }
}
