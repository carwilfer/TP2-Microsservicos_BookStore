package com.infnet.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:8082")
public interface CustomerClient {
    @GetMapping("/api/customers/{id}")
    ResponseEntity<Object>  getCustomerById(@PathVariable("id") Long id);
}
