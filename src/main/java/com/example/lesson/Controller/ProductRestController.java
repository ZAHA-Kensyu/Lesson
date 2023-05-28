package com.example.lesson.Controller;

import com.example.lesson.Entity.ProductRecord;
import com.example.lesson.Service.ProductService;
import com.example.lesson.form.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductRestController {
    //Productsテーブルのレコードをすべて取得する
    @Autowired ProductService productService;

    //確認ok
    @GetMapping("/api/products")
    List<ProductRecord> findAll(){
        System.out.println("findAllリクエスト");
        return productService.findAll();
    }

    //確認ok
    @GetMapping("/api/product/{id}")
    ProductRecord findById(@PathVariable int id){
        System.out.println("findByIdリクエスト"+id);
        return productService.findById(id);
    }



    @PostMapping("/api/product")
    public void insert(@RequestBody ProductInfo productInfo){
        System.out.println("id"+productInfo.getId());
        System.out.println("name"+productInfo.getName());
        System.out.println("price"+productInfo.getPrice());
        System.out.println("insertリクエスト");
    }

    @PutMapping("/api/product/{id}")
    public void update(@RequestBody ProductRecord productRecord,@PathVariable int id){
        System.out.println("updateリクエスト");
    }

    @DeleteMapping("/api/product/{id}")
    public void delete(@PathVariable int id){
        System.out.println("deleteリクエスト");
    }

}
