package com.example.sf1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public int calculateTotalProduct() {
        List<Product> box = productRepository.getBox();
        int total = 0;
        for (Product product : box) total++;
        return total;
    }

    public int calculateAverageCost() {
        List<Product> box = productRepository.getBox();
        if (box.size() == 0) return 0;
        int cost = 0;
        for (Product product : box) cost = cost + product.getCost();
        int average = cost / box.size();
        return average;
    }

    public void showBox() {
        List<Product> box = productRepository.getBox();
        for (Product product : box) System.out.println(product);
    }

    public void addProduct(Product product) {
        List<Product> box = productRepository.getBox();
        box.add(product);
    }

    public void findProduct(int id) {
        List<Product> box = productRepository.getBox();
        for (Product product : box) {
            if (product.getId() == id) System.out.println(product);
        }
    }

    public void updateCost(int id, int cost) {
        List<Product> box = productRepository.getBox();
        for (Product product : box) {
            if (product.getId() == id) product.setCost(cost);
        }
    }

    public void delProduct(int id) {
        List<Product> box = productRepository.getBox();
        for (Product product : box) {
            if (product.getId() == id) box.remove(product);
        }
    }
}
