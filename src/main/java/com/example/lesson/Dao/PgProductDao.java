package com.example.lesson.Dao;

import com.example.lesson.Entity.ProdubtRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PgProductDao implements ProductDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<ProdubtRecord> findAll(){
        return jdbcTemplate.query("SELECT * FROM products",new DataClassRowMapper<>(ProdubtRecord.class));
    }

    @Override
    public ProdubtRecord findById(int id){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id",id);
        var list = namedParameterJdbcTemplate.query("SELECT * FROM products WHERE id = :id",param,new DataClassRowMapper<>(ProdubtRecord.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int insert(ProdubtRecord produbtRecord){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name",produbtRecord.name());
        param.addValue("price",produbtRecord.price());
        return namedParameterJdbcTemplate.update("INSERT INTO products (name , price) VALUES (:name, :price)",param);
    }

    @Override
    public int update(ProdubtRecord produbtRecord){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id",produbtRecord.id());
        param.addValue("name",produbtRecord.name());
        param.addValue("price",produbtRecord.price());
        return namedParameterJdbcTemplate.update("UPDATE products SET name = :name, price = :price WHERE id = :id",param);
    }

    @Override
    public int delete(int id){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id",id);
        return namedParameterJdbcTemplate.update("DELETE FROM products WHERE id = :id",param);
    }
}
