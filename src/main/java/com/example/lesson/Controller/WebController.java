package com.example.lesson.Controller;

import com.example.lesson.Entity.ProductRecord;
import com.example.lesson.Service.PgProductService;
import com.example.lesson.form.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


public class WebController {

    //フィールド
    //DBの取得
    @Autowired
    PgProductService productService;

    //受け取り処理
    @GetMapping("product-list")
    public String output(Model model) {
        //DBから取得したやつ
        var products = productService.findAll();

        model.addAttribute("products",products);
        return "product-list";
    }

    //リダイレクト

    @GetMapping("/product/{id}")
    public String product(@PathVariable("id")int id, Model model){
        var product = productService.findById(id);
        model.addAttribute("product",product);
        return "FindById";
    }

    @GetMapping("/product-add")
    public String productAdd(@ModelAttribute("product") ProductInfo product){
        return "/product-add";
    }

    //POSTで流れてきた
    @PostMapping("/product-add")
    public String productView(@Validated @ModelAttribute("product") ProductInfo product , BindingResult bindingResult) {

        System.out.println("入っているか?");
        if(bindingResult.hasErrors()){
            System.out.println("エラー");
            return "/product-add";
        }else{
            System.out.println("成功");
            productService.insert(new ProductRecord(null,product.getName(), product.getPrice()));
            //リダイレクトを行いたいときはredirect:を付けないといけない。
            System.out.println("リダイレクト");
            return "redirect:/product-list";
        }
    }

    @GetMapping("/product/update/{id}")
    public String getProductUpdate(@PathVariable("id")int id,@ModelAttribute("products") ProductInfo product){
        product.setId(id);
        return "product-update";
    }

    @PostMapping("/product/update/{id}")
    public String postProductUpdate(@PathVariable("id")int id , @Validated @ModelAttribute("products") ProductInfo product, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            System.out.println("エラー");
        }else{
            var productUpdateObj = new ProductRecord(product.getId(), product.getName(), product.getPrice());
            var number = productService.update(productUpdateObj);
            var message = number != 0 ? "更新できました。":"更新できませんでした";
            System.out.println(message);
        }
        return "product-update";
    }

    @PostMapping("/product-delete/{id}")
    public String postDelete(@PathVariable("id")int id){
        var num = productService.delete(id);
        if(num != 0) {
            System.out.println("削除しました");
        }else {
            System.out.println("削除できませんでした。");
        }
        return "redirect:/product-list";
    }
    //300リダイレクトステータスコード
    //200成功
}
