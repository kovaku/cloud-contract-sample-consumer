package com.github.kovaku.consumer.service;

import com.github.kovaku.consumer.config.FibonacciException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FibonacciService {

    private static final String FIBONACCI_FIELD_NAME = "fibonacci";
    private final FibonacciClient client;

    public FibonacciService(FibonacciClient client) {
        this.client = client;
    }

    public String getSingleFibonacci(Integer nth) {
        String fibonacci;
        try {
            fibonacci = client.getFibonacci(nth).get(FIBONACCI_FIELD_NAME);
        } catch (FibonacciException e) {
            fibonacci = e.getMessage();
        }
        return fibonacci;
    }

    public Map<Integer, String> getMultipleFibonacci(Set<Integer> nthSet) {
        Map<Integer, String> results = new ConcurrentHashMap<>(nthSet.size());
        nthSet.parallelStream()
                .forEach(nth -> results.put(nth, getSingleFibonacci(nth)));
        return results;
    }
}
