package com.shop.products;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class Product {
    protected String name;
    protected double price;
}