package com.programming.ecommerce.payload.request;

import lombok.Data;

@Data
public class CategoryRequest {
    private String id;
    private String name;
    private String description;

}
