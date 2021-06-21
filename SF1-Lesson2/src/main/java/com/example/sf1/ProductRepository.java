package com.example.sf1;

import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> box;

    @PostConstruct
    public void init() {
        box = new ArrayList<>();
    }

    public List<Product> getBox() {
        return box;
    }

}