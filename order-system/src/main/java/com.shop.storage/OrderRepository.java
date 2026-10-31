package com.shop.storage;

import com.shop.products.Product;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class OrderRepository<T extends Product> {
    private final List<T> orders = new CopyOnWriteArrayList<>();

    public void add(T product) {
        orders.add(product);
    }

    public List<T> getAll() {
        return List.copyOf(orders);
    }
}