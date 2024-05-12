package com.programming.ecommerce.models;

import com.programming.ecommerce.payload.request.ProductRequest;
import lombok.Data;

import java.util.List;

@Data
public class OrderedProductResponseDto {
    private List<ProductRequest> productRequestList;
    private Long orderAmount;
}
