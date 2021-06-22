package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.model.ProductModel;
import com.geekbrains.spring.mvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

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
}
