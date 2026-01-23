package com.example.demo.product.infrastructure.database;

import com.example.demo.product.domain.Product;
import com.example.demo.product.domain.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdructRepositoryImpl implements ProductRepository {

    private List<Product> products;

    public ProdructRepositoryImpl() {
        this.products = new ArrayList<>();
    }

    @Override
    public void upsert(Product product) {
        products.add(product);
    }

    @Override
    public Optional<Product> findById(long id) {
        return products.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void deleteById(int id) {
        products.remove(findById(id));
    }
}
