package com.infnet.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service", url = "http://localhost:8083")
public interface BookClient {
    @GetMapping("/books/{id}")
    ResponseEntity<Object> getBookById(@PathVariable("id") Long id);
}
