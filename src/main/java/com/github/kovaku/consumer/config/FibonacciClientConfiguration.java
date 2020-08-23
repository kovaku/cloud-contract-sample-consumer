package com.github.kovaku.consumer.config;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

import java.nio.charset.Charset;

public class FibonacciClientConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            Exception exception;
            if(response.status() == 400) {
                exception = new FibonacciException(response.reason());
            } else {
                exception = new FibonacciException(String.format("Technical error while calling fibonacci service, status: %s", response.status()));
            }
            return exception;
        };
    }
}
