package com.programming.ecommerce.models;

import com.programming.ecommerce.payload.request.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private Long price;
    private byte[] image;
    @DBRef
    private Category category;
    public String getImageBase64() {
        return Base64.getEncoder().encodeToString(this.image);
    }

    public ProductRequest getProductRequest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setId(id);
        productRequest.setName(name);
        productRequest.setDescription(description);
        productRequest.setPrice(price);
        if (category != null)
            productRequest.setCategoryId(category.getId());
            productRequest.setCategoryName(category.getName());
        productRequest.setImageBase64(getImageBase64());
        return productRequest;
    }
}
