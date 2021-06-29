package com.example.netshop.services;

import com.example.netshop.exceptions.ProductNotFoundException;
import com.example.netshop.model.ProductModel;
import com.example.netshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public int calculateTotalProduct() {
        List<ProductModel> box = productRepository.getBox();
        int total = 0;
        for (ProductModel product : box) total++;
        return total;
    }

    public int calculateAverageCost() {
        List<ProductModel> box = productRepository.getBox();
        if (box.size() == 0) return 0;
        int cost = 0;
        for (ProductModel product : box) cost = cost + product.getCost();
        int average = cost / box.size();
        return average;
    }

    public List<ProductModel> getBox() {
        List<ProductModel> box = productRepository.getBox();
        return box;
    }

    public ProductModel findById(Integer id) {
        return productRepository
                .findByID(id)
                .orElseThrow(() -> new ProductNotFoundException("Продукт с id: " + id + " не существует!"));
    }
}
