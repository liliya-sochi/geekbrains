package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.ProductModel;
import com.geekbrains.spring.mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public String products(Model model) {
        List<ProductModel> box = productService.getBox();
        model.addAttribute("box", box);
        return "products";
    }
}
