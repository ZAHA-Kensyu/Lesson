package com.example.lesson.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/product-management")
    String productList(){
        return "test";
    }
}
