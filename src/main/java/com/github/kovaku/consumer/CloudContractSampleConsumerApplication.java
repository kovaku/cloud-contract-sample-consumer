package com.github.kovaku.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CloudContractSampleConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudContractSampleConsumerApplication.class, args);
	}

}
