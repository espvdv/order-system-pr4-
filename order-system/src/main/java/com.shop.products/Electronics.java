package com.shop.products;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Electronics extends Product {
    private String model;
}