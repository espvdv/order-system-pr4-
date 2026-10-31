package com.shop.products;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Clothing extends Product {
    private String size;
}