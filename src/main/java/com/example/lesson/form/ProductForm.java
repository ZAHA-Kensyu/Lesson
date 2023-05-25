package com.example.lesson.form;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ProductForm {
    private int id;
    @NotEmpty() //入力必須
    @Length(min = 1,max = 50)//1文字以上、50文字以下
    private String name;
    @NotNull()
    @Min(value = 0)
    @Digits(integer = 9, fraction = 0,message = "{Digits.products.price}")
    private Integer price;
}
