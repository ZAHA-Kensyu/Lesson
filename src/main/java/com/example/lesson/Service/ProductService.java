package com.example.lesson.Service;

import com.example.lesson.Entity.ProductRecord;

import java.util.List;

public interface ProductService {
    List<ProductRecord> findAll();

    ProductRecord findById(int id);

    int insert(ProductRecord productRecord);

    int update(ProductRecord productRecord);

    int delete(int id);
}
