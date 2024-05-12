package com.programming.ecommerce.service.admin.adminproduct;

import com.programming.ecommerce.models.Category;
import com.programming.ecommerce.models.Product;
import com.programming.ecommerce.payload.request.ProductRequest;
import com.programming.ecommerce.repository.CategoryRepository;
import com.programming.ecommerce.repository.ProductRepository;
import com.programming.ecommerce.service.admin.admincategory.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService adminCategoryService;
    @Override
    public ProductRequest addProduct(ProductRequest productRequest, MultipartFile image) throws IOException {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        Optional<Category> cat = adminCategoryService.getCategoryById(productRequest.getCategoryId());
        if(cat.isPresent()){
            product.setCategory(cat.get());
        }
        product.setImage(image.getBytes());

        return productRepository.save(product).getProductRequest();

    }

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
    public void deleteProduct(String id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
        }
    }

    @Override
    public ProductRequest getProductById(String productId){
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            return product.get().getProductRequest();
        }
        return null;
    }

    @Override
    public ProductRequest updateProduct(String productId, ProductRequest productRequest) {
        Optional<Product> product = productRepository.findById(productId);
        Optional<Category> category = categoryRepository.findById(productRequest.getCategoryId());
        if(product.isPresent() && category.isPresent()){
            Product product1 = product.get();
            product1.setName(productRequest.getName());
            product1.setDescription(productRequest.getDescription());
            product1.setPrice(productRequest.getPrice());
            product1.setCategory(category.get());
            return productRepository.save(product1).getProductRequest();

        }
        return null;
    }

}
