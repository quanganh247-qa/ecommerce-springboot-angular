package com.programming.ecommerce.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "payments")
public class Payment {
    @Id
    private String id;
    private String cardholderName;
    private String cardNumber;
    private LocalDate expirationDate;
    private String cvv;
}
