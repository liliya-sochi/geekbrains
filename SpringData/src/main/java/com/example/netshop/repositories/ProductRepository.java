package com.example.netshop.repositories;

import com.example.netshop.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    Optional<ProductModel> findByTitle(String title);

    List<ProductModel> findAllByCostGreaterThanEqual(int max);
    List<ProductModel> findAllByCostLessThanEqual(int min);
    List<ProductModel> findAllByCostGreaterThanEqualAndCostLessThanEqual(int min, int max);
}
