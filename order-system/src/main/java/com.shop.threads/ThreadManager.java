package com.shop.threads;

import com.shop.products.Product;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {
    private final ExecutorService executor;

    public ThreadManager(int threads) {
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public void shutdown() {
        executor.shutdown();
    }
}