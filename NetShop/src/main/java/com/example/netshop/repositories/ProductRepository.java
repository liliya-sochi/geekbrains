package com.example.netshop.repositories;

import com.example.netshop.model.ProductModel;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<ProductModel> findByID(Integer id) {
        return box.stream()
                .filter(s -> s.getId()==id)
                .findFirst();
    }
}
