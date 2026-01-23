package com.example.demo.product.infrastructure.api;

import com.example.demo.product.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductApi {

    //Get all
    ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String pageSize);

    //Get by ID
    ResponseEntity<Product> getProductById(@PathVariable Long id);

    //Create
    ResponseEntity<Void> saveProduct(@RequestBody Product product);

    //Update
    ResponseEntity<Product> updateProduct(@RequestBody Product product);

    //Delete
    ResponseEntity<Void> deleteProduct(@PathVariable Long id);

}
