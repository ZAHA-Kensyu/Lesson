package com.example.lesson;

import com.example.lesson.Service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LessonApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
		SpringApplication.run(LessonApplication.class, args);

		ProductService productService = context.getBean(ProductService.class);
//		var list = productService.findAll();
//		list.stream().forEach(System.out::println);
//
//		var s = productService.findById(1);
//		System.out.println("取得"+s);
//
//		System.out.println("インサート" + productService.insert(new ProdubtRecord(0,"test",20)));
//
//		System.out.println("更新" + productService.update(new ProdubtRecord(1,"test",20)));
//
//		System.out.println("削除" + productService.delete(2));
	}

}
