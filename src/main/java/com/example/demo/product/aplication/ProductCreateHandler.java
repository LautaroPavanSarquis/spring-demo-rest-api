package com.example.demo.product.aplication;

import com.example.demo.Common.RequestHandler;
import com.example.demo.product.domain.Product;
import com.example.demo.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCreateHandler implements RequestHandler<ProductCreateRequest, Void> {


    private ProductRepository productRepository;


    public ProductCreateHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Void handler(ProductCreateRequest request) {

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(request.getImage())
                .build();

        productRepository.upsert(product);

        return null;
    }

    //Define el tiplo de clase de entrada
    @Override
    public Class<ProductCreateRequest> getRequestType() {
        return null;
    }
}
