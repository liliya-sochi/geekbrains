package com.example.netshop.controllers;

import com.example.netshop.models.ProductModel;
import com.example.netshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping
    public List<ProductModel> getAllProducts() {
        return (List<ProductModel>) productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProductModel getProductById(@PathVariable Long id) {
        return productRepository.findById(id).get();
    }

    @PostMapping
    public ProductModel saveProduct(@RequestBody ProductModel product) {
        return productRepository.save(product);
    }

    @GetMapping("delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        ProductModel product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

    @GetMapping("/find")
    public ProductModel findProductByTitle(@RequestParam String title) {
        return productRepository.findByTitle(title).get();
    }

    @GetMapping("/cost")
    public List<ProductModel> findProductByTitle(@RequestParam Integer min, @RequestParam Integer max) {
        // Подскажите, пожалуйста, как правильно сделать проверку, если параметры отсутствуют?
        // Ни "==", ни "equals" не работают! Ведь если параметр отсутствеут, он равент null?
        // Просто не хочется создавать три GetMapping для каждого случая!
        if (min == null) return productRepository.findAllByCostGreaterThanEqual(max);
        if (max.equals(null)) return productRepository.findAllByCostLessThanEqual(min);
        else return productRepository.findAllByCostGreaterThanEqualAndCostLessThanEqual(min, max);
    }
}
