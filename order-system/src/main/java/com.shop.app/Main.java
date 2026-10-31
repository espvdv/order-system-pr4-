package com.shop.app;

import com.github.javafaker.Faker;
import com.shop.processing.OrderProcessor;
import com.shop.products.Clothing;
import com.shop.products.Electronics;
import com.shop.products.Product;
import com.shop.storage.OrderRepository;
import com.shop.threads.ThreadManager;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws Exception {
        Faker faker = new Faker(new Locale("uk"));

        // Використання Lombok @Builder та JavaFaker
        Clothing shirt = Clothing.builder()
                .name("Футболка")
                .size(faker.superhero().prefix() + " L")
                .price(faker.number().randomDouble(2, 20, 100))
                .build();

        Electronics phone = Electronics.builder()
                .name("Смартфон")
                .model(faker.app().name())
                .price(faker.number().randomDouble(2, 500, 2000))
                .build();

        OrderRepository<Product> repo = new OrderRepository<>();
        repo.add(shirt);
        repo.add(phone);
        
        // Додаємо товар з поганою ціною для перевірки exceptions
        repo.add(Electronics.builder().name("Брак").model("Bad").price(-10).build());

        ThreadManager threadManager = new ThreadManager(3);
        OrderProcessor<Product> processor = new OrderProcessor<>(threadManager);

        List<? extends Future<?>> futures = processor.processAll(
                repo.getAll(),
                p -> p.getPrice() > 0,               // Лямбда
                OrderProcessor::applyDiscount        // Method reference
        );

        for (var f : futures) f.get();
        
        threadManager.shutdown();
        System.out.println("Done.");
    }
}