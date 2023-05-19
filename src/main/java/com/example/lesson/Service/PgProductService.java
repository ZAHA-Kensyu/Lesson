package com.example.lesson.Service;

import com.example.lesson.Dao.ProductDao;
import com.example.lesson.Entity.ProdubtRecord;
import com.example.lesson.Exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PgProductService implements ProductService{

    @Autowired
    ProductDao productDao;

    @Override
    public List<ProdubtRecord> findAll(){
        try{
            return productDao.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ProdubtRecord findById(int id) throws ProductNotFoundException {
        try{
            return productDao.findById(id);
        }catch (ProductNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(ProdubtRecord produbtRecord){
        try{
            return productDao.insert(produbtRecord);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(ProdubtRecord produbtRecord){
            return productDao.update(produbtRecord);
    }

    @Override
    public int delete(int id){
            return productDao.delete(id);
    }
}
