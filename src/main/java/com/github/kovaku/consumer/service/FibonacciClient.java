package com.github.kovaku.consumer.service;

import com.github.kovaku.consumer.config.FibonacciClientConfiguration;
import com.github.kovaku.consumer.config.FibonacciException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "fibonacci-client", url = "${fibonacci.server.url}", configuration = FibonacciClientConfiguration.class)
public interface FibonacciClient {

    @GetMapping(path = "/fibonacci", headers = {"client-id=fibo-consumer", "content-type=application/json"})
    Map<String, String> getFibonacci(@RequestParam int nth) throws FibonacciException;

}
