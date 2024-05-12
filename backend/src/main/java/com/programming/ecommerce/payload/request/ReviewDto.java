package com.programming.ecommerce.payload.request;

import com.programming.ecommerce.models.Product;
import com.programming.ecommerce.models.User;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewDto {
    private String id;
    private Long rating;
    private String description;
    private MultipartFile img;
    private byte[] returnedImg;
    private String userId;
    private String productId;
    private String username;
}
