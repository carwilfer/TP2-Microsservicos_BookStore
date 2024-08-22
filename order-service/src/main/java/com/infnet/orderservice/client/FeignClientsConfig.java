package com.infnet.orderservice.client;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.infnet.orderservice.client")
public class FeignClientsConfig {
}
