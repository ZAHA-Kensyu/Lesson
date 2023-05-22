package com.example.lesson.Controller;

import com.example.lesson.Dao.PgProductDao;
import com.example.lesson.Entity.ProdubtRecord;
import com.example.lesson.Service.PgProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebController {

    //フィールド
    //DBの取得
    @Autowired
    PgProductService productService;

    //受け取り処理
    @GetMapping("roduct-list")
    public String output(Model model) {
        //DBから取得したやつ
        var products = productService.findAll();

        model.addAttribute("products",products);
        return "roduct-list";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable("id")int id, Model model){
        var product = productService.findById(id);
        model.addAttribute("product",product);
        return "FindById";
    }
}
