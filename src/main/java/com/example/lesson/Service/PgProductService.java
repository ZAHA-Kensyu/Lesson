package com.example.lesson.Service;

import com.example.lesson.Dao.ProductDao;
import com.example.lesson.Entity.ProductRecord;
import com.example.lesson.Exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PgProductService implements ProductService{

    @Autowired
    ProductDao productDao;

    @Override
    public List<ProductRecord> findAll(){
        try{
            return productDao.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ProductRecord findById(int id) throws ProductNotFoundException {
        try{
            return productDao.findById(id);
        }catch (ProductNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(ProductRecord productRecord){
        try{
            return productDao.insert(productRecord);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(ProductRecord productRecord){
            return productDao.update(productRecord);
    }

    @Override
    public int delete(int id){
            return productDao.delete(id);
    }
}
