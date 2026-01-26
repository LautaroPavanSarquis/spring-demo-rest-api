package com.example.demo.product.infrastructure.api;

import com.example.demo.Common.Mediator;
import com.example.demo.product.aplication.ProductCreateRequest;
import com.example.demo.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {


    private Mediator mediator;


    //CRUD
    // Get all
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String pageSize) {

        return ResponseEntity.ok(products);
    }

    //Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = products.stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst()
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Product not found")
                );

        return ResponseEntity.ok(product);
    }

    //Create
    @PostMapping
    public ResponseEntity<Void> saveProduct(@RequestBody Product product) {

        mediator.dispatch(new ProductCreateRequest(product.getName(), product.getDescription(), product.getPrice(), product.getImage()));

        return ResponseEntity.created(URI.create("/api/v1/products/" + product.getId())).build();
    }

    //Edit / Update
    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {

        Product productSelected = products.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .findFirst()
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Product not found")
                );

        productSelected.setName(product.getName());
        productSelected.setDescription(product.getDescription());
        productSelected.setPrice(product.getPrice());
        productSelected.setImage(product.getImage());

        return ResponseEntity.ok(product);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        products.removeIf(p -> p.getId().equals(id));

        return ResponseEntity.noContent().build();
    }

}
