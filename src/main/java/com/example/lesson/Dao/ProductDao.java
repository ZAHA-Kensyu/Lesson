package com.example.lesson.Dao;

import com.example.lesson.Entity.ProdubtRecord;

import java.util.List;

public interface ProductDao {
    List<ProdubtRecord> findAll();

    ProdubtRecord findById(int id);

    int insert(ProdubtRecord produbtRecord);

    int update(ProdubtRecord produbtRecord);

    int delete(int id);

}
