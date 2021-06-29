package com.example.netshop.controllers;

import com.example.netshop.model.ProductModel;
import com.example.netshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public String products(Model model) {
        List<ProductModel> box = productService.getBox();
        model.addAttribute("box", box);
        return "products";
    }

    @GetMapping("/find")
    public String getById(Model model, @RequestParam(required = false, name = "id") Integer id) {
        model.addAttribute("product", productService.findById(id));
        return "find";
    }
}
