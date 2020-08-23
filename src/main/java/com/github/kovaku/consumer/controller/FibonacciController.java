package com.github.kovaku.consumer.controller;

import com.github.kovaku.consumer.service.FibonacciService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
public class FibonacciController {
    private final FibonacciService service;

    public FibonacciController(FibonacciService service) {
        this.service = service;
    }

    @PostMapping("/fibonacci")
    public Map<Integer, String> getMultipleFibonacci(@RequestBody Set<Integer> fibonacci) {
        return service.getMultipleFibonacci(fibonacci);
    }
}
