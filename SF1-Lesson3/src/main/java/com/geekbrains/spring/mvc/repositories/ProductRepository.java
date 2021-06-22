package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.ProductModel;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<ProductModel> box;

    @PostConstruct
    public void init() {
        box = new ArrayList<>();
        box.add(new ProductModel(001, "Apple", 10));
        box.add(new ProductModel(002, "Oil", 5));
        box.add(new ProductModel(003, "Sugar", 22));
        box.add(new ProductModel(004, "Tomato", 10));
        box.add(new ProductModel(005, "Bread", 1));
    }

    public List<ProductModel> getBox() {
        return box;
    }

    public void showBox() {
        for (ProductModel product : box) System.out.println(product);
    }

    public void addProduct(ProductModel product) {
        box.add(product);
    }

    public void findProduct(int id) {
        for (ProductModel product : box) {
            if (product.getId() == id) System.out.println(product);
        }
    }

    public void updateCost(int id, int cost) {
        for (ProductModel product : box) {
            if (product.getId() == id) product.setCost(cost);
        }
    }

    public void delProduct(int id) {
        for (ProductModel product : box) {
            if (product.getId() == id) box.remove(product);
        }
    }
}
