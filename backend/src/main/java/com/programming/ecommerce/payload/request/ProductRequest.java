package com.programming.ecommerce.payload.request;

import com.programming.ecommerce.models.Category;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductRequest {
        private String id;
        private String name;
        private String description;
        private Long price;
        private byte[] byteImg;
        private String imageBase64;
        private String categoryId;
        public String categoryName;
        private MultipartFile img;
        private Long quantity;
}
