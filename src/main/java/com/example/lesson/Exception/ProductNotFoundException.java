package com.example.lesson.Exception;

public class ProductNotFoundException extends RuntimeException{
    ProductNotFoundException(String massage){
        super(massage);
    }
}
