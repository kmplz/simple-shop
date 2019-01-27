package com.simple.shop.core.domain;

import lombok.Data;

@Data
public class Product {

    private Integer id;
    private String name;
    private String description;
    private Category category;
    private Brand brand;
    private Double price;
}
