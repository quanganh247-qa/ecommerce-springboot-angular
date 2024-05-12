package com.programming.ecommerce.payload.request;

import com.programming.ecommerce.models.Product;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class FAQDto {

    private String id;
    private String question;
    private String answer;
    private String productId;

}
