package com.example.lesson.Service;

import com.example.lesson.Entity.ProdubtRecord;

import java.util.List;

public interface ProductService {
    List<ProdubtRecord> findAll();

    ProdubtRecord findById(int id);

    int insert(ProdubtRecord produbtRecord);

    int update(ProdubtRecord produbtRecord);

    int delete(int id);
}
