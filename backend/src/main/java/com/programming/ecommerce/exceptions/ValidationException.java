package com.programming.ecommerce.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }
}
