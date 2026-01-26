package com.example.demo.product.aplication;

import com.example.demo.Common.Request;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductCreateRequest implements Request<Void> {

    private String name;
    private String description;
    private double price;
    private String image;

}
